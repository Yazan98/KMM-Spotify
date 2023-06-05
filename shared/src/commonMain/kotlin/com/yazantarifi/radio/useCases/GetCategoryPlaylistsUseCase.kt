package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.playlists.GetPlaylistsByCategoryApiRequest
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist
import com.yazantarifi.radio.models.SpotifyFeaturedPlaylistsResponse


class GetCategoryPlaylistsUseCase: SopifyUseCase<GetCategoryPlaylistsUseCase.RequestParams, List<RadioPlaylist>>() {

    private var nextPageUrl: String = ""
    private val apiClient: GetPlaylistsByCategoryApiRequest by lazy {
        GetPlaylistsByCategoryApiRequest()
    }

    data class RequestParams(
        val categoryId: String,
        val token: String
    )

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override fun clear() {
        super.clear()
        apiClient.clear()
    }

    override suspend fun build(requestValue: RequestParams) {
        if (apiClient.isRequestListenerAttachNeeded()) {
            apiClient.addHttpClient(getHttpClientInstance())
            apiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyFeaturedPlaylistsResponse> {
                override fun onSuccess(responseValue: SpotifyFeaturedPlaylistsResponse) {
                    val playlists = ArrayList<RadioPlaylist>()
                    nextPageUrl = responseValue.playlists?.next ?: ""
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

                    onSubmitLoadingState(false)
                    onSubmitSuccessState(playlists)
                }

                override fun onError(error: Throwable) {
                    onSubmitExceptionState(error)
                }
            })
        }

        onSubmitLoadingState(true)
        val headers = SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue.token)
        apiClient.executeRequest(GetPlaylistsByCategoryApiRequest.RequestParams(
            nextPageUrl,
            requestValue.categoryId
        ), headers)
    }

}
