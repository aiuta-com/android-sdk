package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.local

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config.toDTO
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TryOnCategoryLocalDataSource(
    private val database: AiutaTryOnDatabase,
) {
    private val tryOnCategoryQueries by lazy { database.tryOnCategoryQueries }
    private val tryOnModelQueries by lazy { database.tryOnModelQueries }

    suspend fun getCategories(): List<TryOnModelsCategory> = withContext(Dispatchers.IO) {
        database.transactionWithResult {
            val categories = tryOnCategoryQueries.selectAll().executeAsList()
            categories.map { category ->
                TryOnModelsCategory(
                    category = category,
                    models = tryOnModelQueries
                        .selectByCategory(category_id = category)
                        .executeAsList()
                        .map { it.toDTO() },
                )
            }
        }
    }

    suspend fun replaceCategories(newCategories: List<TryOnModelsCategory>) = withContext(Dispatchers.IO) {
        database.transaction {
            // Clean first
            tryOnCategoryQueries.removeAll()
            tryOnModelQueries.removeAll()

            // Insert new
            newCategories.forEach { category ->
                tryOnCategoryQueries.insert(category.category)
                category.models.map { model ->
                    tryOnModelQueries.insert(
                        id = model.id,
                        url = model.url,
                        type = model.type,
                        category_id = category.category,
                    )
                }
            }
        }
    }

    companion object {
        fun getInstance(): TryOnCategoryLocalDataSource = TryOnCategoryLocalDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
