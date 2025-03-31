package domain.interactor.session

import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import domain.interactor.utils.newImageList
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class SelectedHolderTest {
    private lateinit var selectedHolder: SelectedHolder<GeneratedImageUIModel>

    @Before
    fun setup() {
        selectedHolder = SelectedHolder()
    }

    @Test
    fun `check ConcurrentModificationException on remove by for`(): Unit =
        runBlocking {
            // Prepare
            selectedHolder.putAll(newImageList(range = (0..500)))

            // Test
            val images = selectedHolder.getList()
            images.forEach { image -> selectedHolder.remove(image) }

            // Check
            assertEquals(
                expected = 0,
                actual = selectedHolder.getList().size,
            )
        }

    @Test
    fun `check ConcurrentModificationException on remove`(): Unit =
        runBlocking {
            // Prepare
            selectedHolder.putAll(newImageList(range = (0..500)))

            // Test
            val images = selectedHolder.getList()
            selectedHolder.remove(images)

            // Check
            assertEquals(
                expected = 0,
                actual = selectedHolder.getList().size,
            )
        }
}
