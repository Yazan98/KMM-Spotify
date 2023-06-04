package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.home.SpotifyFeaturedPlaylistsApiRequest
import com.yazantarifi.radio.core.shared.compose.components.models.HomeHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomePlaylistsItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist
import com.yazantarifi.radio.models.SpotifyFeaturedPlaylistsResponse
import io.ktor.client.HttpClient

class GetHomeScreenItemsUseCase constructor(
    private val httpClient: HttpClient
): SopifyUseCase<GetHomeScreenItemsUseCase.RequestValue, List<RadioHomeItem>>() {

    private val featuredListApiClient: SpotifyFeaturedPlaylistsApiRequest by lazy {
        SpotifyFeaturedPlaylistsApiRequest()
    }

    data class RequestValue(
        val token: String
    )

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: RequestValue) {
        val screenItems = arrayListOf<RadioHomeItem>()
        onSubmitLoadingState(true)

        screenItems.add(HomeHeaderItem(
            RadioApplicationMessages.getMessage("home_title"),
            RadioApplicationMessages.getMessage("home_des"),
        ))

        screenItems.add(HomeLayoutDesignItem(
            HomeLayoutDesignItem.SCROLL_H,
            RadioApplicationMessages.getMessage("home_change_design"),
        ))

        if (featuredListApiClient.isRequestListenerAttachNeeded()) {
            featuredListApiClient.addHttpClient(httpClient)
            featuredListApiClient.addRequestListener(object : SopifyRequestListener<SpotifyFeaturedPlaylistsResponse> {
                override fun onSuccess(responseValue: SpotifyFeaturedPlaylistsResponse) {
                    val playlists = ArrayList<RadioPlaylist>()
                    responseValue.playlists?.items?.let {
                        playlists.addAll(it.map {  playlist ->
                            var imageUrl: String = ""
                            playlist.images?.forEach {
                                imageUrl = it.url ?: ""
                            }

                            RadioPlaylist(
                                id = playlist.id ?: "",
                                image = imageUrl,
                                name = playlist.name ?: "",
                                ownerName = playlist.owner?.name,
                                numberOfTracks = playlist.tracks?.total ?: 0
                            )
                        })
                    }

                    screenItems.add(HomePlaylistsItem(
                        RadioApplicationMessages.getMessage("featured_playlists"),
                        RadioApplicationMessages.getMessage("loading_image"),
                        playlists
                    ))

                    onSubmitLoadingState(false)
                    onSubmitSuccessState(screenItems)
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        featuredListApiClient.executeRequest(Unit, SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue.token))
    }

    override fun clear() {
        super.clear()
        featuredListApiClient.clear()
    }
}