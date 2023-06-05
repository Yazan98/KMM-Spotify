package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseAlias
import io.ktor.client.HttpClient
import javax.inject.Inject

class GetCategoryPlaylistsUseCaseAlias @Inject constructor(): SopifyUseCaseAlias<GetCategoryPlaylistsUseCase>()
