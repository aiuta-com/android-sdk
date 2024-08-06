import com.aiuta.fashionsdk.publicModules
import com.aiuta.fashionsdk.setupBomPublishing
import com.android.build.gradle.LibraryExtension

plugins {
    id("java-platform")
}

setupBomPublishing<LibraryExtension>()

dependencies {
    constraints {
        for (subproject in rootProject.subprojects) {
            if (subproject.name in publicModules) {
                api(subproject)
            }
        }
    }
}
