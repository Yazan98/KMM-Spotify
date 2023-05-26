package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
import com.yazantarifi.radio.api.RedditAuthApiRequest
import com.yazantarifi.radio.models.RedditAuthResponse
import io.ktor.client.HttpClient

class GetAccessTokenUseCase constructor(
    private val httpClient: HttpClient?
): SopifyUseCase<String, RedditAuthResponse>() {

    private val apiClient: RedditAuthApiRequest by lazy {
        RedditAuthApiRequest()
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: String) {
        apiClient.addHttpClient(httpClient)
        if (apiClient.isRequestListenerAttachNeeded()) {
            apiClient.addRequestListener(object: SopifyRequestListener<RedditAuthResponse> {
                override fun onSuccess(responseValue: RedditAuthResponse) {
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
            apiClient.executeRequest(requestValue)
        }
    }

    override fun clear() {
        super.clear()
        apiClient.clear()
    }

}
