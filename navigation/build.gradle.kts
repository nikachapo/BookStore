import dependencies.AndroidTestDependencies
import dependencies.Dependencies
import dependencies.SupportDependencies
import dependencies.TestDependencies

plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT)
}

android {
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK

        testInstrumentationRunner = AndroidTestDependencies.INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Java.JAVA_VERSION
    }
}

dependencies {
    // Dependencies
    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.HILT)

    // Annotation processing
    kapt(AnnotationProcessing.HILT_COMPILER)

    // Support
    implementation(SupportDependencies.APPCOMPAT)

    // Testing
    testImplementation(TestDependencies.JUNIT4)
    androidTestImplementation(AndroidTestDependencies.EXT_JUNIT)
}