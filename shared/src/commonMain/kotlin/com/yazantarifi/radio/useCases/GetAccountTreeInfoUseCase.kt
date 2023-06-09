package com.yazantarifi.radio.useCases

import com.yazantarifi.radio.base.api.SopifyRequestListener
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.account.GetSpotifyAccountApiRequest
import com.yazantarifi.radio.api.account.SpotifyGetAccountFollowingsArtistsApiRequest
import com.yazantarifi.radio.api.account.SpotifyLastPlayedTracksApiRequest
import com.yazantarifi.radio.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.composable.models.account.AccountArtistItem
import com.yazantarifi.radio.composable.models.account.AccountHeaderItem
import com.yazantarifi.radio.composable.models.account.AccountListSectionItem
import com.yazantarifi.radio.composable.models.account.AccountSectionNameItem
import com.yazantarifi.radio.composable.models.account.AccountTrackItem
import com.yazantarifi.radio.composable.models.account.RadioAccountItem
import com.yazantarifi.radio.mappers.RadioNumberFormatter
import com.yazantarifi.radio.models.SpotifyAccountResponse
import com.yazantarifi.radio.models.SpotifyArtistsResponse
import com.yazantarifi.radio.models.SpotifyTracksResponse

class GetAccountTreeInfoUseCase constructor(): SopifyUseCase<String, List<RadioAccountItem>>() {

    private val artistsApiClient: SpotifyGetAccountFollowingsArtistsApiRequest by lazy {
        SpotifyGetAccountFollowingsArtistsApiRequest()
    }

    private val accountApiClient: GetSpotifyAccountApiRequest by lazy {
        GetSpotifyAccountApiRequest()
    }

    private val playedListApiClient: SpotifyLastPlayedTracksApiRequest by lazy {
        SpotifyLastPlayedTracksApiRequest()
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: String) {
        val screenItems = ArrayList<RadioAccountItem>()
        if (accountApiClient.isRequestListenerAttachNeeded()) {
            accountApiClient.addHttpClient(getHttpClientInstance())
            accountApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyAccountResponse> {
                override fun onSuccess(responseValue: SpotifyAccountResponse) {
                    var image = "https://cdn.icon-icons.com/icons2/1863/PNG/512/account-circle_119476.png"
                    responseValue.images?.forEach {
                        image = it.url ?: ""
                    }

                    screenItems.add(
                        AccountSectionNameItem(
                            RadioApplicationMessages.getMessage("account_section_info")
                        )
                    )

                    screenItems.add(
                        AccountHeaderItem(
                            image,
                            responseValue.name ?: "",
                            responseValue.email ?: "",
                            responseValue.type ?: ""
                        )
                    )

                    screenItems.add(
                        AccountSectionNameItem(
                            RadioApplicationMessages.getMessage("account_section_info_more")
                        )
                    )

                    screenItems.add(
                        AccountListSectionItem(
                            RadioApplicationMessages.getMessage("account_section_followers"),
                            RadioNumberFormatter.addCommasToNumber("${responseValue.followers?.total ?: 0}")
                        )
                    )

                    screenItems.add(
                        AccountListSectionItem(
                            RadioApplicationMessages.getMessage("account_section_product"),
                            responseValue.product ?: ""
                        )
                    )
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        if (artistsApiClient.isRequestListenerAttachNeeded()) {
            artistsApiClient.addHttpClient(getHttpClientInstance())
            artistsApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyArtistsResponse> {
                override fun onSuccess(responseValue: SpotifyArtistsResponse) {
                    screenItems.add(
                        AccountSectionNameItem(
                            RadioApplicationMessages.getMessage("account_section_following_artists")
                        )
                    )

                    responseValue.artists?.items?.forEach {
                        var description = ""
                        it.genres?.forEach {
                            description += "$it . "
                        }

                        screenItems.add(
                            AccountArtistItem(
                                it.name ?: "",
                                description.dropLast(2),
                                RadioNumberFormatter.addCommasToNumber("${it.followers?.total ?: 0}"),
                                it.images?.get(0)?.url ?: "",
                                it.id ?: ""
                            )
                        )
                    }
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        if (playedListApiClient.isRequestListenerAttachNeeded()) {
            playedListApiClient.addHttpClient(getHttpClientInstance())
            playedListApiClient.addRequestListener(object :
                SopifyRequestListener<SpotifyTracksResponse> {
                override fun onSuccess(responseValue: SpotifyTracksResponse) {
                    screenItems.add(
                        AccountSectionNameItem(
                            RadioApplicationMessages.getMessage("account_section_played_tracks")
                        )
                    )

                    responseValue.items?.forEach {
                        it.track?.let {
                            var image = ""
                            var description = ""
                            if (!it.album?.images.isNullOrEmpty()) {
                                image = it.album?.images?.get(0)?.url ?: ""
                            }

                            it.artists?.forEach {
                                description += "${it.name} . "
                            }

                            screenItems.add(
                                AccountTrackItem(
                                image,
                                it.name ?: "",
                                description.dropLast(2),
                                it.id ?: "",
                                it.previewPlayUrl ?: ""
                            )
                            )
                        }
                    }
                }

                override fun onError(error: Throwable) {
                    // Will Not Show the Item
                }
            })
        }

        onSubmitLoadingState(true)
        val headers = SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue)
        accountApiClient.executeRequest(Unit, headers)
        artistsApiClient.executeRequest(Unit, headers)
        playedListApiClient.executeRequest(Unit, headers)

        onSubmitLoadingState(false)
        onSubmitSuccessState(screenItems)
    }

    override fun clear() {
        super.clear()
        accountApiClient.clear()
        artistsApiClient.clear()
        playedListApiClient.clear()
    }

}
