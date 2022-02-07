import dependencies.AndroidTestDependencies
import dependencies.Dependencies
import dependencies.SupportDependencies
import dependencies.TestDependencies

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.DAGGER_HILT)
    id(Plugins.SQLDELIGHT)
}

android {
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        applicationId = App.BOOKSTORE
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK
        versionCode = Releases.VERSION_CODE
        versionName = Releases.VERSION_NAME

        testInstrumentationRunner = AndroidTestDependencies.INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            buildConfigField(type = "String", name = "BASE_URL", value = BuildTypeDebug.baseUrl)

            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        getByName(BuildTypes.RELEASE) {
            buildConfigField(type = "String", name = "BASE_URL", value = BuildTypeRelease.baseUrl)

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResources
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Java.JAVA_VERSION
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":paging"))


    // Dependencies
    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.ANNOTATION)
    implementation(Dependencies.HILT)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.CONVERTER_GSON)
    implementation(Dependencies.COIL)
    implementation(Dependencies.LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT_COROUTINES_ADAPTER)
    implementation(Dependencies.SQLDELIGHT_ANDROID)
    implementation(Dependencies.SQLDELIGHT_COROUTINES_EXTENSION)

    // Annotation processing
    kapt(AnnotationProcessing.HILT_COMPILER)

    // Support
    implementation(SupportDependencies.APPCOMPAT)
    implementation(SupportDependencies.CONSTRAINT_LAYOUT)
    implementation(SupportDependencies.MATERIAL_DESIGN)
    implementation(SupportDependencies.LEGACY_SUPPORT)

    // Testing
    testImplementation(TestDependencies.JUNIT4)
    testImplementation(TestDependencies.TEST_COROUTINES)
    testImplementation(TestDependencies.MOCKK)
    testImplementation(TestDependencies.TEST_SQLDELIGHT)
    androidTestImplementation(AndroidTestDependencies.EXT_JUNIT)
}

sqldelight {
    database(SQLDelightConfig.DATABASE_NAME) {
        packageName = App.BOOKSTORE
        sourceFolders = listOf(SQLDelightConfig.SOURCE_FOLDER_1)

        schemaOutputDirectory = file(SQLDelightConfig.SCHEMA_OUTPUT_DIRECTORY_PATH)
        verifyMigrations = true
    }
}
