package com.yazantarifi.radio.android.home

import com.yazantarifi.radio.useCases.GetDiscoverContentUseCase
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
    fun getGetDiscoverContentUseCase(httpClient: HttpClient): GetDiscoverContentUseCase {
        return GetDiscoverContentUseCase(httpClient)
    }

    @Provides
    @Singleton
    fun getGetHomeScreenItemsUseCase(httpClient: HttpClient): GetHomeScreenItemsUseCase {
        return GetHomeScreenItemsUseCase(httpClient)
    }

}