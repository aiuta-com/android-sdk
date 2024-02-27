package sample.tryon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sample.tryon.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup insets
        enableEdgeToEdge()

        setContent {
            MainScreen()
        }
    }
}
