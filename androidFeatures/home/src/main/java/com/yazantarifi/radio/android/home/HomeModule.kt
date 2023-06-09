package com.yazantarifi.radio.android.home

import com.yazantarifi.radio.useCases.GetAccountTreeInfoUseCase
import com.yazantarifi.radio.useCases.GetCategoriesUseCase
import com.yazantarifi.radio.useCases.GetHomeScreenItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun getGetAccountTreeInfoUseCase(httpClient: HttpClient): GetAccountTreeInfoUseCase {
        return GetAccountTreeInfoUseCase().apply {
            addHttpClient(httpClient)
        }
    }

    @Provides
    @Singleton
    fun getGetCategoriesUseCase(httpClient: HttpClient): GetCategoriesUseCase {
        return GetCategoriesUseCase().apply {
            addHttpClient(httpClient)
        }
    }

    @Provides
    @Singleton
    fun getGetHomeScreenItemsUseCase(httpClient: HttpClient): GetHomeScreenItemsUseCase {
        return GetHomeScreenItemsUseCase().apply {
            addHttpClient(httpClient)
        }
    }

}