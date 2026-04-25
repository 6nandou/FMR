plugins {
    id("com.android.application")
    kotlin("android")
}

val extName by extra("LectorTMO VIP")
val pkgNameSuffix by extra("es.lectortmovip")
val extClass by extra(".LectorTmovip")
val extVersionCode by extra(1)
val isNsfw by extra(true)

dependencies {
    implementation("androidx.preference:preference:1.2.1")
}
