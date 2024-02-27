package sample.tryon

import android.app.Application
import com.aiuta.fashionsdk.Fashion

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFashion()
    }

    private fun initFashion() {
        fashion =
            Fashion.Builder()
                .setApiKey(API_KEY)
                .setApplication(this)
                .build()
    }

    companion object {
        lateinit var fashion: Fashion

        const val API_KEY = "YOUR_API_KEY_HERE"
    }
}
