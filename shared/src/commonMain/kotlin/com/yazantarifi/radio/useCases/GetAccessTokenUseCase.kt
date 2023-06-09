package com.yazantarifi.radio.useCases

import com.yazantarifi.radio.base.api.SopifyRequestListener
import com.yazantarifi.radio.api.SpotifyApiHeadersBuilder
import com.yazantarifi.radio.api.SpotifyAuthApiRequest
import com.yazantarifi.radio.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.models.SpotifyAuthResponse

class GetAccessTokenUseCase constructor(): SopifyUseCase<String, SpotifyAuthResponse>() {

    private val apiClient: SpotifyAuthApiRequest by lazy {
        SpotifyAuthApiRequest()
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: String) {
        apiClient.addHttpClient(getHttpClientInstance())
        if (apiClient.isRequestListenerAttachNeeded()) {
            apiClient.addRequestListener(object: SopifyRequestListener<SpotifyAuthResponse> {
                override fun onSuccess(responseValue: SpotifyAuthResponse) {
                    onSubmitSuccessState(responseValue)
                }

                override fun onError(error: Throwable) {
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
