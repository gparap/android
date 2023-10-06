pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        //needed for android-pdf-viewer
        maven("https://jcenter.bintray.com")
    }
}

rootProject.name = "PDF Reader"
include(":app")
