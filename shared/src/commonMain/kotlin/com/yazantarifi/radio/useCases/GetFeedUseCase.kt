package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
//import com.yazantarifi.radio.api.home.HomeScreenFeedApiRequest
import com.yazantarifi.radio.models.RedditFeedPayload
import com.yazantarifi.radio.models.RedditFeedResponse
import io.ktor.client.HttpClient

class GetFeedUseCase constructor(
    private val httpClient: HttpClient?
): SopifyUseCase<GetFeedUseCase.RequestValue, RedditFeedPayload>() {

//    private val feedApiRequest: HomeScreenFeedApiRequest by lazy {
//        HomeScreenFeedApiRequest()
//    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override suspend fun build(requestValue: RequestValue) {
//        feedApiRequest.addHttpClient(httpClient)
//        if (feedApiRequest.isRequestListenerAttachNeeded()) {
//            feedApiRequest.addRequestListener(object : SopifyRequestListener<RedditFeedResponse> {
//                override fun onSuccess(responseValue: RedditFeedResponse) {
//                    onSubmitSuccessState(RedditFeedPayload(true, responseValue.payload?.children?.map { it.post }))
//                    onSubmitLoadingState(false)
//                }
//
//                override fun onError(error: Throwable) {
//                    onSubmitLoadingState(false)
//                    onSubmitExceptionState(error)
//                }
//            })
//        }

        onSubmitLoadingState(true)
//        feedApiRequest.executeRequest(requestValue.accessToken ?: "", arrayListOf())
    }

    override fun clear() {
        super.clear()
//        feedApiRequest.clear()
    }

    data class RequestValue(
        val after: String? = "",
        val accessToken: String? = ""
    )

}
