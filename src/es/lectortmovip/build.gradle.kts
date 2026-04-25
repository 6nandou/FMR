plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "eu.kanade.tachiyomi.extension.es.lectortmovip"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}

val extName by extra("LectorTMO VIP")
val pkgNameSuffix by extra("es.lectortmovip")
val extClass by extra(".LectorTmovip")
val extVersionCode by extra(1)
val isNsfw by extra(true)

dependencies {
    implementation("androidx.preference:preference:1.2.1")
    implementation("org.jsoup:jsoup:1.17.2")
}
