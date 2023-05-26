package com.yazantarifi.radio.android

import android.content.Context
import com.yazantarifi.kmm.sopy.base.context.SopifyContext
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageKeyValue
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun getStorageProviderImplementationInstance(@ApplicationContext context: Context): SopifyStorageProvider {
        return SopifyStorageProvider(SopifyStorageKeyValue(context.applicationContext as SopifyContext))
    }

}
