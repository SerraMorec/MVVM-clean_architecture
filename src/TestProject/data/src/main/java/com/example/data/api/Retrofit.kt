package com.example.data.api

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


fun serverApi(baseUrl: String): ProductApi {
    return retrofit(baseUrl).create()
}

fun retrofit(
    baseUrl: String
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttp())
        .build()
}

fun okHttp(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()
}
