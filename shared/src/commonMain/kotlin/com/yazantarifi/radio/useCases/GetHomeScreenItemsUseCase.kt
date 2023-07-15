package com.yazantarifi.radio.useCases

import com.yazantarifi.radio.base.api.SopifyRequestListener
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.home.SpotifyFeaturedPlaylistsApiRequest
import com.yazantarifi.radio.api.home.SpotifyGetCategoriesApiRequest
import com.yazantarifi.radio.api.home.SpotifyGetCategoryPlaylistApiRequest
import com.yazantarifi.radio.api.home.SpotifyNewAlbumsReleasesApiRequest
import com.yazantarifi.radio.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.composable.models.*
import com.yazantarifi.radio.composable.models.items.RadioAlbum
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
import com.yazantarifi.radio.composable.models.items.RadioPlaylist
import com.yazantarifi.radio.models.SpotifyAlbumsResponse
import com.yazantarifi.radio.models.SpotifyCategoriesResponse
import com.yazantarifi.radio.models.SpotifyFeaturedPlaylistsResponse

class GetHomeScreenItemsUseCase constructor(): SopifyUseCase<GetHomeScreenItemsUseCase.RequestValue, List<RadioHomeItem>>() {

    private val featuredListApiClient: SpotifyFeaturedPlaylistsApiRequest by lazy {
        SpotifyFeaturedPlaylistsApiRequest()
    }

    private val newReleasesApiClient: SpotifyNewAlbumsReleasesApiRequest by lazy {
        SpotifyNewAlbumsReleasesApiRequest()
    }

    private val categoriesApiClient: SpotifyGetCategoriesApiRequest by lazy {
        SpotifyGetCategoriesApiRequest(false)
    }

    private val playlistsCategoryApiClient: SpotifyGetCategoryPlaylistApiRequest by lazy {
        SpotifyGetCategoryPlaylistApiRequest()
    }

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
            featuredListApiClient.addHttpClient(getHttpClientInstance())
            featuredListApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyFeaturedPlaylistsResponse> {
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
                                numberOfTracks = playlist.tracks?.total ?: 0,
                                RadioApplicationMessages.getMessage("loading_image")
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
            newReleasesApiClient.addHttpClient(getHttpClientInstance())
            newReleasesApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyAlbumsResponse> {
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
                                artists = album.artists?.map { it.name ?: "" } ?: arrayListOf(),
                                loadingMessage = RadioApplicationMessages.getMessage("loading_image"),
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
            categoriesApiClient.addHttpClient(getHttpClientInstance())
            categoriesApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyCategoriesResponse> {
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
                                imageUrl,
                                RadioApplicationMessages.getMessage("loading_image")
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

        if (playlistsCategoryApiClient.isRequestListenerAttachNeeded()) {
            playlistsCategoryApiClient.addHttpClient(getHttpClientInstance())
            playlistsCategoryApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyFeaturedPlaylistsResponse> {
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
                                numberOfTracks = playlist.tracks?.total ?: 0,
                                RadioApplicationMessages.getMessage("loading_image")
                            )
                        })
                    }

                    screenItems.add(HomePlaylistsItem(
                        responseValue.sectionName ?: "",
                        RadioApplicationMessages.getMessage("loading_image"),
                        playlists
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

        if (requestValue.isNotificationPermissionShouldShow) {
            screenItems.add(HomeNotificationPermissionItem(
                true,
                RadioApplicationMessages.getMessage("notification_permission_warning_message"),
                RadioApplicationMessages.getMessage("notification_permission_title"),
                "",
                RadioApplicationMessages.getMessage("notification_permission_message"),
                RadioApplicationMessages.getMessage("notification_permission_enable"),
                RadioApplicationMessages.getMessage("notification_permission_disable"),
                requestValue.isNotificationsEnabled
            ))
        }

        playlistsCategoryApiClient.executeRequest(SpotifyGetCategoryPlaylistApiRequest.RequestParams(
            RadioApplicationMessages.getMessage("top_pop"),
            "0JQ5DAqbMKFEC4WFtoNRpw"
        ), headers)

        playlistsCategoryApiClient.executeRequest(SpotifyGetCategoryPlaylistApiRequest.RequestParams(
            RadioApplicationMessages.getMessage("top_mood"),
            "0JQ5DAqbMKFzHmL4tf05da"
        ), headers)

        playlistsCategoryApiClient.executeRequest(SpotifyGetCategoryPlaylistApiRequest.RequestParams(
            RadioApplicationMessages.getMessage("top_gaming"),
            "0JQ5DAqbMKFCfObibaOZbv"
        ), headers)

        playlistsCategoryApiClient.executeRequest(SpotifyGetCategoryPlaylistApiRequest.RequestParams(
            RadioApplicationMessages.getMessage("top_workout"),
            "0JQ5DAqbMKFAXlCG6QvYQ4"
        ), headers)

        screenItems.add(HomeOpenSpotifyAppItem(
            RadioApplicationMessages.getMessage("spotify_app_open_title"),
            RadioApplicationMessages.getMessage("spotify_app_open_message"),
            RadioApplicationMessages.getMessage("spotify_app_open_button"),
        ))

        onSubmitLoadingState(false)
        onSubmitSuccessState(screenItems)
    }

    data class RequestValue(
        val token: String,
        val isNotificationPermissionShouldShow: Boolean,
        val isNotificationsEnabled: Boolean
    )

    override fun clear() {
        super.clear()
        featuredListApiClient.clear()
        newReleasesApiClient.clear()
        categoriesApiClient.clear()
        playlistsCategoryApiClient.clear()
    }
}