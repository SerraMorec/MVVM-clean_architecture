package com.example.testproject.app

import com.example.data.api.ProductApi
import com.example.data.api.serverApi
import com.example.data.repository.RepositoryProductsImpl
import com.example.domain.repository.RepositoryProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val BASE_URL = "https://dummyjson.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesProductApi():ProductApi {
        return serverApi(BASE_URL)
    }

    @Provides
    @Singleton
    fun providesRepository():RepositoryProducts {
        return RepositoryProductsImpl(providesProductApi())
    }
}