package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.SpotifyAuthApiRequest
import com.yazantarifi.radio.models.SpotifyAuthResponse
import io.ktor.client.HttpClient

class GetAccessTokenUseCase constructor(
    private val httpClient: HttpClient?
): SopifyUseCase<String, SpotifyAuthResponse>() {

    private val apiClient: SpotifyAuthApiRequest by lazy {
        SpotifyAuthApiRequest()
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: String) {
        apiClient.addHttpClient(httpClient)
        if (apiClient.isRequestListenerAttachNeeded()) {
            apiClient.addRequestListener(object: SopifyRequestListener<SpotifyAuthResponse> {
                override fun onSuccess(responseValue: SpotifyAuthResponse) {
                    onSubmitLoadingState(false)
                    onSubmitSuccessState(responseValue)
                }

                override fun onError(error: Throwable) {
                    onSubmitLoadingState(false)
                    onSubmitExceptionState(error)
                }
            })
        }

        if (requestValue.isNotEmpty()) {
            onSubmitLoadingState(true)
            apiClient.executeRequest(requestValue, SpotifyApiHeadersBuilder.getApplicationAuthHeader())
        }
    }

    override fun clear() {
        super.clear()
        apiClient.clear()
    }

}
