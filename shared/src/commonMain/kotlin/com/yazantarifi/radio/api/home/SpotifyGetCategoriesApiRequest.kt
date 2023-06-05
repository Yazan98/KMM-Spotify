package com.yazantarifi.radio.api.home

import com.yazantarifi.kmm.sopy.api.SopifyOneRequest
import com.yazantarifi.radio.models.SpotifyCategoriesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class SpotifyGetCategoriesApiRequest constructor(
    private val isAllCategories: Boolean
): SopifyOneRequest<Unit, SpotifyCategoriesResponse>() {

    override fun getRequestUrl(): String {
        return when (isAllCategories) {
            true -> "https://api.spotify.com/v1/browse/categories?offset=0&limit=40"
            false -> "https://api.spotify.com/v1/browse/categories?offset=0&limit=20"
        }
    }

    override suspend fun executeRequest(requestBody: Unit, headers: List<Pair<String, String>>) {
        try {
            val response = httpClient?.get(getRequestUrl()) {
                headers.forEach {
                    header(it.first, it.second)
                }
            }

            if (isSuccessResponse(response?.status ?: HttpStatusCode.BadRequest)) {
                response?.body<SpotifyCategoriesResponse>()?.let {
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