package sample.tryon.kmp.ui

import android.content.Context
import android.widget.Toast

fun Context.makeToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT,
    ).show()
}
