package sample.tryon.kmp

import android.app.Application
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFashion()
    }

    private fun initFashion() {
        aiuta =
            Aiuta.Builder()
                .setAuthenticationStrategy(
                    authenticationStrategy =
                        ApiKeyAuthenticationStrategy(
                            apiKey = BuildConfig.SAMPLES_TRYON_API_KEY,
                        ),
                )
                .setSubscriptionId(BuildConfig.SAMPLES_TRYON_SUBSCRIPTION_ID)
                .setPlatformContext(AiutaPlatformContext(application = this))
                .build()
    }

    companion object {
        lateinit var aiuta: Aiuta
    }
}
