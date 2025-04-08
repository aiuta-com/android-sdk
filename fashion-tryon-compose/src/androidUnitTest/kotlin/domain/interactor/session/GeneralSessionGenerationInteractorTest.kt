package domain.interactor.session

import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session.GeneralSessionGenerationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.warmup.WarmUpInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toSessionUiModel
import domain.interactor.utils.newImageList
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlin.test.assertEquals
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GeneralSessionGenerationInteractorTest {
    private val mockWarmUpInteractor = mockk<WarmUpInteractor>(relaxed = true)

    private lateinit var generalSessionGenerationInteractor: GeneralSessionGenerationInteractor

    @Before
    fun setup() {
        generalSessionGenerationInteractor =
            GeneralSessionGenerationInteractor(
                warmUpInteractor = mockWarmUpInteractor,
            )
    }

    @Test
    fun `check concurrent adding elements by addGeneration`(): Unit = runBlocking {
        // Prepare
        coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

        val imageList1 = newImageList(range = (0..100))
        val imageList2 = newImageList(range = (101..200))
        val imageList3 = newImageList(range = (201..300))

        // Test
        joinAll(
            launch {
                imageList1.forEach { generalSessionGenerationInteractor.addGeneration(it) }
            },
            launch {
                imageList2.forEach { generalSessionGenerationInteractor.addGeneration(it) }
            },
            launch {
                imageList3.forEach { generalSessionGenerationInteractor.addGeneration(it) }
            },
        )

        // Check
        val expected =
            (imageList1 + imageList2 + imageList3).map { it.toSessionUiModel() }.sortedBy { it.id }
        val actual = generalSessionGenerationInteractor.sessionGenerations.sortedBy { it.id }

        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `check concurrent adding elements by addGenerations`(): Unit = runBlocking {
        // Prepare
        coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

        val imageList1 = newImageList(range = (0..100))
        val imageList2 = newImageList(range = (101..200))
        val imageList3 = newImageList(range = (201..300))

        // Test
        joinAll(
            launch { generalSessionGenerationInteractor.addGenerations(imageList1) },
            launch { generalSessionGenerationInteractor.addGenerations(imageList2) },
            launch { generalSessionGenerationInteractor.addGenerations(imageList3) },
        )

        // Check
        val expected =
            (imageList1 + imageList2 + imageList3).map { it.toSessionUiModel() }.sortedBy { it.id }
        val actual = generalSessionGenerationInteractor.sessionGenerations.sortedBy { it.id }

        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `check concurrent deleting element`(): Unit = runBlocking {
        // Prepare
        coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

        val imageList1 = newImageList(range = (0..100))
        val imageList2 = newImageList(range = (101..200))
        val imageList3 = newImageList(range = (201..300))
        generalSessionGenerationInteractor.addGenerations(imageList1)
        generalSessionGenerationInteractor.addGenerations(imageList2)
        generalSessionGenerationInteractor.addGenerations(imageList3)

        // Test
        joinAll(
            launch { generalSessionGenerationInteractor.deleteGenerations(imageList1) },
            launch { generalSessionGenerationInteractor.deleteGenerations(imageList2) },
            launch { generalSessionGenerationInteractor.deleteGenerations(imageList3) },
        )

        // Check
        assertEquals(
            expected = 0,
            actual = generalSessionGenerationInteractor.sessionGenerations.size,
        )
    }

    @Test
    fun `check ConcurrentModificationException on remove generation`(): Unit = runBlocking {
        // Prepare
        coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

        generalSessionGenerationInteractor.addGenerations(newImageList(range = (0..500)))

        // Test
        val sessionGeneration = generalSessionGenerationInteractor.sessionGenerations

        sessionGeneration.forEach { generation ->
            generalSessionGenerationInteractor.deleteGeneration(generation)
        }

        // Check
        assertEquals(
            expected = 0,
            actual = generalSessionGenerationInteractor.sessionGenerations.size,
        )
    }
}
