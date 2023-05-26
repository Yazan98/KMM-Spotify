package com.yazantarifi.radio.android.home

import com.yazantarifi.radio.useCases.GetFeedUseCase
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
    fun getGetFeedUseCase(httpClient: HttpClient): GetFeedUseCase {
        return GetFeedUseCase(httpClient)
    }

}