package com.aiuta.fashionsdk.tryon.compose.data.internal.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.sqldelight.Query
import app.cash.sqldelight.SuspendingTransacter
import app.cash.sqldelight.Transacter
import app.cash.sqldelight.TransacterBase
import app.cash.sqldelight.TransactionCallbacks
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/**
 * This is adopted version of paging source with Paging3 support.
 *
 * See reference [here](https://github.com/sqldelight/sqldelight/blob/9b3b3491be036ad63d5892c7b20ad4ae2bf3aa9c/extensions/androidx-paging3/src/commonMain/kotlin/app/cash/sqldelight/paging3/OffsetQueryPagingSource.kt)
 */
@Suppress("FunctionName")
internal fun <RowType : Any> QueryPagingSource(
    countQuery: Query<Long>,
    transacter: TransacterBase,
    context: CoroutineContext = Dispatchers.IO,
    queryProvider: (limit: Long, offset: Long) -> Query<RowType>,
    initialOffset: Long = 0,
): PagingSource<Int, RowType> = OffsetQueryPagingSource(
    { limit, offset -> queryProvider(limit.toLong(), offset.toLong()) },
    countQuery.toInt(),
    transacter,
    context,
    initialOffset.toInt(),
)

private fun Query<Long>.toInt(): Query<Int> = object : Query<Int>({ cursor -> mapper(cursor).toInt() }) {
    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>) = this@toInt.execute(mapper)
    override fun addListener(listener: Listener) = this@toInt.addListener(listener)
    override fun removeListener(listener: Listener) = this@toInt.removeListener(listener)
}

internal class OffsetQueryPagingSource<RowType : Any>(
    private val queryProvider: (limit: Int, offset: Int) -> Query<RowType>,
    private val countQuery: Query<Int>,
    private val transacter: TransacterBase,
    private val context: CoroutineContext = Dispatchers.IO,
    private val initialOffset: Int = 0,
) : PagingSource<Int, RowType>() {

    override val jumpingSupported get() = true

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, RowType> = withContext(context) {
        val key = params.key ?: initialOffset
        val limit = when (params) {
            is LoadParams.Prepend -> minOf(key, params.loadSize)
            else -> params.loadSize
        }
        val getPagingSourceLoadResult: TransactionCallbacks.() -> LoadResult.Page<Int, RowType> =
            {
                val count = countQuery.executeAsOne()
                val offset = when (params) {
                    is LoadParams.Prepend -> maxOf(0, key - params.loadSize)
                    is LoadParams.Append -> key
                    is LoadParams.Refresh -> if (key >= count - params.loadSize) {
                        maxOf(
                            0,
                            count - params.loadSize,
                        )
                    } else {
                        key
                    }
                }
                val data = queryProvider(limit, offset).executeAsList()
                val nextPosToLoad = offset + data.size

                LoadResult.Page(
                    data = data,
                    prevKey = offset.takeIf { it > 0 && data.isNotEmpty() },
                    nextKey = nextPosToLoad.takeIf { data.isNotEmpty() && data.size >= limit && it < count },
                    itemsBefore = offset,
                    itemsAfter = maxOf(0, count - nextPosToLoad),
                )
            }
        val loadResult = when (transacter) {
            is Transacter -> transacter.transactionWithResult(bodyWithReturn = getPagingSourceLoadResult)
            is SuspendingTransacter -> transacter.transactionWithResult(bodyWithReturn = getPagingSourceLoadResult)
        }

        if (invalid) {
            LoadResult.Error(
                throwable = IllegalStateException("Failed to load new page, params - $params"),
            )
        } else {
            loadResult
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RowType>) = state.anchorPosition?.let { maxOf(0, it - (state.config.initialLoadSize / 2)) }
}
