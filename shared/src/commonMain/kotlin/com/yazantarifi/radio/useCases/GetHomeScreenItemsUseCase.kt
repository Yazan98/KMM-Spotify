package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.home.SpotifyFeaturedPlaylistsApiRequest
import com.yazantarifi.radio.api.home.SpotifyGetCategoriesApiRequest
import com.yazantarifi.radio.api.home.SpotifyNewAlbumsReleasesApiRequest
import com.yazantarifi.radio.core.shared.compose.components.models.HomeAlbumsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeCategoriesItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomePlaylistsItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioAlbum
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioCategoryItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist
import com.yazantarifi.radio.models.SpotifyAlbum
import com.yazantarifi.radio.models.SpotifyAlbumsResponse
import com.yazantarifi.radio.models.SpotifyCategoriesResponse
import com.yazantarifi.radio.models.SpotifyFeaturedPlaylistsResponse
import io.ktor.client.HttpClient

class GetHomeScreenItemsUseCase constructor(
    private val httpClient: HttpClient
): SopifyUseCase<GetHomeScreenItemsUseCase.RequestValue, List<RadioHomeItem>>() {

    private val featuredListApiClient: SpotifyFeaturedPlaylistsApiRequest by lazy {
        SpotifyFeaturedPlaylistsApiRequest()
    }

    private val newReleasesApiClient: SpotifyNewAlbumsReleasesApiRequest by lazy {
        SpotifyNewAlbumsReleasesApiRequest()
    }

    private val categoriesApiClient: SpotifyGetCategoriesApiRequest by lazy {
        SpotifyGetCategoriesApiRequest(false)
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
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        if (newReleasesApiClient.isRequestListenerAttachNeeded()) {
            newReleasesApiClient.addHttpClient(httpClient)
            newReleasesApiClient.addRequestListener(object : SopifyRequestListener<SpotifyAlbumsResponse> {
                override fun onSuccess(responseValue: SpotifyAlbumsResponse) {
                    val albums = ArrayList<RadioAlbum>()
                    responseValue.albums?.items?.let {
                        albums.addAll(it.map { album ->
                            var imageUrl: String = ""
                            album.images?.get(0)?.let {
                                imageUrl = it.url ?: ""
                            }

                            RadioAlbum(
                                id = album.id ?: "",
                                name = album.name ?: "",
                                image = imageUrl ?: "",
                                releaseDate = album.releaseDate ?: "",
                                numberOfTracks = album.numberOfTracks ?: 0,
                                artists = album.artists?.map { it.name ?: "" } ?: arrayListOf()
                            )
                        })
                    }

                    screenItems.add(HomeAlbumsItem(
                        RadioApplicationMessages.getMessage("albums"),
                        RadioApplicationMessages.getMessage("loading_image"),
                        albums
                    ))
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        if (categoriesApiClient.isRequestListenerAttachNeeded()) {
            categoriesApiClient.addHttpClient(httpClient)
            categoriesApiClient.addRequestListener(object : SopifyRequestListener<SpotifyCategoriesResponse> {
                override fun onSuccess(responseValue: SpotifyCategoriesResponse) {
                    val categories = ArrayList<RadioCategoryItem>()
                    responseValue.categories?.items?.let {
                        categories.addAll(it.map {
                            var imageUrl: String = ""
                            it.icons?.get(0)?.let {
                                imageUrl = it.url ?: ""
                            }

                            RadioCategoryItem(
                                it.id ?: "",
                                it.name ?: "",
                                imageUrl
                            )
                        })
                    }

                    screenItems.add(HomeCategoriesItem(
                        RadioApplicationMessages.getMessage("categories"),
                        RadioApplicationMessages.getMessage("loading_image"),
                        categories
                    ))
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        val headers = SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue.token)
        featuredListApiClient.executeRequest(Unit, headers)
        newReleasesApiClient.executeRequest(Unit, headers)
        categoriesApiClient.executeRequest(Unit, headers)

        onSubmitLoadingState(false)
        onSubmitSuccessState(screenItems)
    }

    override fun clear() {
        super.clear()
        featuredListApiClient.clear()
        newReleasesApiClient.clear()
        categoriesApiClient.clear()
    }
}