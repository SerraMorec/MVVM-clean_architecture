plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    api(libs.jakarta.inject)
    api(libs.kotlinx.coroutines.core)
    api(libs.paging.common)
}