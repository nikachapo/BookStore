interface BuildTypes {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val baseUrl: String
    val isMinifyEnabled: Boolean
    val isShrinkResources: Boolean
}

object BuildTypeDebug : BuildTypes {
    const val applicationIdSuffix = ".debug"
    override val baseUrl = "\"https://api.itbook.store/1.0/\""

    override val isMinifyEnabled = false
    override val isShrinkResources = false
}

object BuildTypeRelease : BuildTypes {
    override val baseUrl = "\"https://api.itbook.store/1.0/\""

    override val isMinifyEnabled = true
    override val isShrinkResources = true
}
