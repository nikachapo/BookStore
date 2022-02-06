fun String.isNonStable(): Boolean {
    val stableKeyword =
        listOf("GA").any { toUpperCase(java.util.Locale.ROOT).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}
