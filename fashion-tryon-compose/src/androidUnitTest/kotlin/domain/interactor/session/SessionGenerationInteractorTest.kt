package domain.interactor.session

import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session.SessionGenerationInteractor
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

internal class SessionGenerationInteractorTest {
    private val mockWarmUpInteractor = mockk<WarmUpInteractor>(relaxed = true)

    private lateinit var sessionGenerationInteractor: SessionGenerationInteractor

    @Before
    fun setup() {
        sessionGenerationInteractor =
            SessionGenerationInteractor(
                warmUpInteractor = mockWarmUpInteractor,
            )
    }

    @Test
    fun `check concurrent adding elements by addGeneration`(): Unit =
        runBlocking {
            // Prepare
            coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

            val imageList1 = newImageList(range = (0..100))
            val imageList2 = newImageList(range = (101..200))
            val imageList3 = newImageList(range = (201..300))

            // Test
            joinAll(
                launch {
                    imageList1.forEach { sessionGenerationInteractor.addGeneration(it) }
                },
                launch {
                    imageList2.forEach { sessionGenerationInteractor.addGeneration(it) }
                },
                launch {
                    imageList3.forEach { sessionGenerationInteractor.addGeneration(it) }
                },
            )

            // Check
            val expected =
                (imageList1 + imageList2 + imageList3).map { it.toSessionUiModel() }.sortedBy { it.id }
            val actual = sessionGenerationInteractor.sessionGenerations.sortedBy { it.id }

            assertEquals(
                expected = expected,
                actual = actual,
            )
        }

    @Test
    fun `check concurrent adding elements by addGenerations`(): Unit =
        runBlocking {
            // Prepare
            coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

            val imageList1 = newImageList(range = (0..100))
            val imageList2 = newImageList(range = (101..200))
            val imageList3 = newImageList(range = (201..300))

            // Test
            joinAll(
                launch { sessionGenerationInteractor.addGenerations(imageList1) },
                launch { sessionGenerationInteractor.addGenerations(imageList2) },
                launch { sessionGenerationInteractor.addGenerations(imageList3) },
            )

            // Check
            val expected =
                (imageList1 + imageList2 + imageList3).map { it.toSessionUiModel() }.sortedBy { it.id }
            val actual = sessionGenerationInteractor.sessionGenerations.sortedBy { it.id }

            assertEquals(
                expected = expected,
                actual = actual,
            )
        }

    @Test
    fun `check concurrent deleting element`(): Unit =
        runBlocking {
            // Prepare
            coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

            val imageList1 = newImageList(range = (0..100))
            val imageList2 = newImageList(range = (101..200))
            val imageList3 = newImageList(range = (201..300))
            sessionGenerationInteractor.addGenerations(imageList1)
            sessionGenerationInteractor.addGenerations(imageList2)
            sessionGenerationInteractor.addGenerations(imageList3)

            // Test
            joinAll(
                launch { sessionGenerationInteractor.deleteGenerations(imageList1) },
                launch { sessionGenerationInteractor.deleteGenerations(imageList2) },
                launch { sessionGenerationInteractor.deleteGenerations(imageList3) },
            )

            // Check
            assertEquals(
                expected = 0,
                actual = sessionGenerationInteractor.sessionGenerations.size,
            )
        }

    @Test
    fun `check ConcurrentModificationException on remove generation`(): Unit =
        runBlocking {
            // Prepare
            coEvery { mockWarmUpInteractor.warmUp(any()) } just runs

            sessionGenerationInteractor.addGenerations(newImageList(range = (0..500)))

            // Test
            val sessionGeneration = sessionGenerationInteractor.sessionGenerations

            sessionGeneration.forEach { generation ->
                sessionGenerationInteractor.deleteGeneration(generation)
            }

            // Check
            assertEquals(
                expected = 0,
                actual = sessionGenerationInteractor.sessionGenerations.size,
            )
        }
}
