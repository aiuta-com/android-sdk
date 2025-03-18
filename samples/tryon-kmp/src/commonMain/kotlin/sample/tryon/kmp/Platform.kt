package sample.tryon.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
