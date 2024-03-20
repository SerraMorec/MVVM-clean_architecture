plugins {
//    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{

    api(libs.retrofit)
    api(libs.retrofit2.serialization)
    api(libs.converter.gson)

    api(libs.result)
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)

//    api(libs.paging.common)

    api(project(":domain"))
}