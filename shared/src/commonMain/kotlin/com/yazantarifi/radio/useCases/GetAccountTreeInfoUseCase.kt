package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.account.GetSpotifyAccountApiRequest
import com.yazantarifi.radio.api.account.SpotifyGetAccountFollowingsArtistsApiRequest
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountArtistItem
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountListSectionItem
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountSectionNameItem
import com.yazantarifi.radio.core.shared.compose.components.models.account.RadioAccountItem
import com.yazantarifi.radio.mappers.RadioNumberFormatter
import com.yazantarifi.radio.models.SpotifyAccountResponse
import com.yazantarifi.radio.models.SpotifyArtistsResponse
import io.ktor.client.HttpClient

class GetAccountTreeInfoUseCase constructor(
    private val httpClient: HttpClient
): SopifyUseCase<String, List<RadioAccountItem>>() {

    private val artistsApiClient: SpotifyGetAccountFollowingsArtistsApiRequest by lazy {
        SpotifyGetAccountFollowingsArtistsApiRequest()
    }

    private val accountApiClient: GetSpotifyAccountApiRequest by lazy {
        GetSpotifyAccountApiRequest()
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: String) {
        val screenItems = ArrayList<RadioAccountItem>()
        if (accountApiClient.isRequestListenerAttachNeeded()) {
            accountApiClient.addHttpClient(httpClient)
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
            artistsApiClient.addHttpClient(httpClient)
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

        onSubmitLoadingState(true)
        val headers = SpotifyApiHeadersBuilder.getApplicationBearerTokenHeaders(requestValue)
        accountApiClient.executeRequest(Unit, headers)
        artistsApiClient.executeRequest(Unit, headers)

        onSubmitLoadingState(false)
        onSubmitSuccessState(screenItems)
    }

    override fun clear() {
        super.clear()
        accountApiClient.clear()
        artistsApiClient.clear()
    }

}
