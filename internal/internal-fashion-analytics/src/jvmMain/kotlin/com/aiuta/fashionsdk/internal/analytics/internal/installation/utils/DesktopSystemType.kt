package com.aiuta.fashionsdk.internal.analytics.internal.installation.utils

internal sealed interface DesktopSystemType {
    public val userHome: String
    public val userName: String
    public val appName: String

    fun platformPath(): String

    class MacOs(
        public override val userHome: String,
        public override val userName: String,
        public override val appName: String,
    ) : DesktopSystemType {
        override fun platformPath(): String = "/Users/$userName/Library/Application Support/$appName"
    }

    class Linux(
        public override val userHome: String,
        public override val userName: String,
        public override val appName: String,
    ) : DesktopSystemType {
        override fun platformPath(): String = "$userName/$appName"
    }

    class Windows(
        public override val userHome: String,
        public override val userName: String,
        public override val appName: String,
    ) : DesktopSystemType {
        override fun platformPath(): String = "$userHome\\AppData\\Local\\$appName"
    }
}

internal fun solveDesktopSystemType(): DesktopSystemType {
    val userName = System.getProperty("user.name")
    val userHome = System.getProperty("user.home")
    val osName = System.getProperty("os.name")
    val appName = "aiuta"

    return when {
        osName.contains("windows", true) -> DesktopSystemType.Windows(userHome, userName, appName)
        osName.contains("Linux", true) -> DesktopSystemType.Linux(userHome, userName, appName)
        osName.contains("Mac", true) -> DesktopSystemType.MacOs(userHome, userName, appName)
        else -> error("Not supported type of OS")
    }
}
