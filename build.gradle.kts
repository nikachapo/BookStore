import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Build.BUILD_GRADLE)
        classpath(Build.KOTLIN_GRADLE_PLUGIN)
        classpath(Build.HILT_GRADLE_PLUGIN)
        classpath(Build.SQLDELIGHT_PLUGIN)
    }
}

plugins {
    id(Plugins.VERSIONS_PLUGIN) version Versions.VERSIONS_PLUGIN
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply {
        plugin(Plugins.VERSIONS_PLUGIN)
    }
}

tasks {
    register("clean", Delete::class.java) {
        delete(rootProject.buildDir)
    }

    register<DependencyUpdatesTask>("versionsUpdate") {
        rejectVersionIf {
            candidate.version.isNonStable()
        }
    }
}