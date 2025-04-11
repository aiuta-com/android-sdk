package sample.tryon.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Aiuta SDK KMP",
    ) {
        AiutaApp()
    }
}
