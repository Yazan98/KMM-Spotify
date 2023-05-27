package com.yazantarifi.radio.api.home

import com.yazantarifi.kmm.sopy.api.SopifyOneRequest
import com.yazantarifi.radio.api.ApiUrls
import com.yazantarifi.radio.models.RedditFeedResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText

class HomeScreenDiscoverApiRequest: SopifyOneRequest<HomeScreenDiscoverApiRequest.RequestParams, RedditFeedResponse>() {

    override suspend fun executeRequest(requestBody: RequestParams) {
        try {
            val response = httpClient?.get(ApiUrls.DISCOVER.requestUrl + requestBody.requestKey) {
                header("Authorization", "Bearer ${requestBody.token}")
            } ?: return

            if (isSuccessResponse(response.status)) {
                requestListener?.onSuccess(response.body())
            } else {
                requestListener?.onError(Throwable(response.bodyAsText()))
            }
        } catch (ex: Exception) {
            requestListener?.onError(ex)
        }
    }

    data class RequestParams(
        val token: String,
        val requestKey: String
    )

}