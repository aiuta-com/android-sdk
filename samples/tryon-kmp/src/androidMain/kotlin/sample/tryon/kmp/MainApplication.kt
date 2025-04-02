package sample.tryon.kmp

import android.app.Application
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFashion()
    }

    private fun initFashion() {
        aiuta = aiuta {
            authenticationStrategy = ApiKeyAuthenticationStrategy("OLDDEMO") // TODO
            platformContext = AiutaPlatformContext(application = this@MainApplication)
        }
    }

    companion object {
        lateinit var aiuta: Aiuta
    }
}
