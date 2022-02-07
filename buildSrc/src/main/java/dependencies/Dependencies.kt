package dependencies

object Dependencies {
    const val KTX_CORE: String = "androidx.core:core-ktx:${Versions.KTX_CORE}"

    const val LIFECYCLE_VIEWMODEL: String =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"

    const val LIFECYCLE_RUNTIME: String =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"

    const val FRAGMENT_KTX: String = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    const val ANNOTATION: String = "androidx.annotation:annotation:${Versions.ANNOTATION}"

    const val HILT: String = "com.google.dagger:hilt-android:${Versions.HILT}"

    const val RETROFIT: String = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"

    const val CONVERTER_GSON: String = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"

    const val LOGGING_INTERCEPTOR: String =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR}"

    const val RETROFIT_COROUTINES_ADAPTER: String =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT_COROUTINES_ADAPTER}"

    const val SQLDELIGHT_ANDROID = "com.squareup.sqldelight:android-driver:${Versions.SQLDELIGHT}"

    const val SQLDELIGHT_COROUTINES_EXTENSION =
        "com.squareup.sqldelight:coroutines-extensions-jvm:${Versions.SQLDELIGHT}"

    const val LEAKCANARY: String =
        "com.squareup.leakcanary:leakcanary-android:${Versions.LEAKCANARY}"

    const val COROUTINES: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
}
