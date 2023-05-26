package com.yazantarifi.radio.android

import android.content.Context
import com.yazantarifi.kmm.sopy.base.api.HttpBaseClient
import com.yazantarifi.kmm.sopy.base.context.SopifyContext
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageKeyValue
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.radio.android.core.RadioApplicationProps
import com.yazantarifi.radio.useCases.GetAccessTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun getStorageProviderImplementationInstance(@ApplicationContext context: Context): SopifyStorageProvider {
        return SopifyStorageProvider(SopifyStorageKeyValue(context.applicationContext as SopifyContext))
    }

    @Provides
    @Singleton
    fun getGetAccessTokenUseCase(): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(RadioApplicationProps.getHttpClientInstance())
    }

    @Provides
    @Singleton
    fun getHttpClient(): HttpClient {
        return RadioApplicationProps.getHttpClientInstance() ?: HttpBaseClient().httpClient
    }

}
