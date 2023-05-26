package com.yazantarifi.radio.api

import com.yazantarifi.kmm.sopy.api.SopifyOneRequest
import com.yazantarifi.radio.models.RedditAuthResponse
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class RedditAuthApiRequest: SopifyOneRequest<String, RedditAuthResponse>() {

    override suspend fun executeRequest(requestBody: String) {
        try {
            val response = httpClient?.post(ApiUrls.ACCESS_TOKEN.requestUrl + "?grant_type=client_credentials&code=$requestBody&redirect_uri=https://github.com/Yazan98/KMM-Reddit") {
                header("Authorization", "Basic NGhaX0lQMldxT1hoYXRjeHV3OHZvdzpUYk9fbUxtMTNwRERNT0s5enR5cnlQVk9vMC1KbEE=")
            }

            if (isSuccessResponse(response?.status ?: HttpStatusCode.BadRequest)) {
                response?.body<RedditAuthResponse>()?.let {
                    requestListener?.onSuccess(it)
                }
            } else {
                requestListener?.onError(Throwable(response?.bodyAsText()))
            }
        } catch (ex: Exception) {
            requestListener?.onError(ex)
        }
    }

}
