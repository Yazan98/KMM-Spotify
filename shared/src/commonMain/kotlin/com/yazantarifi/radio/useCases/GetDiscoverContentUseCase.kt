package com.yazantarifi.radio.useCases

import com.yazantarifi.kmm.sopy.api.SopifyRequestListener
import com.yazantarifi.kmm.sopy.base.useCases.useCasesTypes.SopifyUseCase
//import com.yazantarifi.radio.api.home.HomeScreenDiscoverApiRequest
import com.yazantarifi.radio.models.RedditFeedPayload
import com.yazantarifi.radio.models.RedditFeedResponse
import io.ktor.client.HttpClient

class GetDiscoverContentUseCase constructor(
    private val httpClient: HttpClient
): SopifyUseCase<GetDiscoverContentUseCase.RequestParams, RedditFeedPayload>() {

//    private val discoverApiInstance: HomeScreenDiscoverApiRequest by lazy {
//        HomeScreenDiscoverApiRequest()
//    }

    data class RequestParams(
        val token: String,
        val key: DiscoverKeys
    )

    enum class DiscoverKeys {
        HOT,
        NEW,
        TOP
    }

    override fun isConstraintsSupported(): Boolean {
        return false
    }

    override fun clear() {
        super.clear()
//        discoverApiInstance.clear()
    }

    override suspend fun build(requestValue: RequestParams) {
        val requestKey = when (requestValue.key) {
            DiscoverKeys.TOP -> "top"
            DiscoverKeys.HOT -> "hot"
            DiscoverKeys.NEW -> "new"
        }

//        discoverApiInstance.addHttpClient(httpClient)
//        if (discoverApiInstance.isRequestListenerAttachNeeded()) {
//            discoverApiInstance.addRequestListener(object :
//                SopifyRequestListener<RedditFeedResponse> {
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
//
//        onSubmitLoadingState(true)
//        discoverApiInstance.executeRequest(
//            HomeScreenDiscoverApiRequest.RequestParams(
//                requestValue.token,
//                requestKey
//            ), arrayListOf()
//        )
    }
}