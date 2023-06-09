package com.yazantarifi.android.radio.playlists

import com.yazantarifi.radio.android.core.RadioApplicationProps
import com.yazantarifi.radio.base.HttpBaseClient
import com.yazantarifi.radio.useCases.GetCategoryPlaylistsUseCase
import com.yazantarifi.radio.useCases.GetCategoryPlaylistsUseCaseAlias
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlaylistsModule {

    @Provides
    fun getGetCategoryPlaylistsUseCase(): GetCategoryPlaylistsUseCaseAlias {
        return GetCategoryPlaylistsUseCaseAlias().apply {
            addInstance(GetCategoryPlaylistsUseCase().apply {
                addHttpClient(RadioApplicationProps.getHttpClientInstance() ?: HttpBaseClient().httpClient)
            })
        }
    }

}

