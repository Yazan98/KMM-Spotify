package com.yazantarifi.radio.api.account

import com.yazantarifi.radio.base.api.SopifyOneRequest
import com.yazantarifi.radio.models.SpotifyArtistsResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class SpotifyGetAccountFollowingsArtistsApiRequest: SopifyOneRequest<Unit, SpotifyArtistsResponse>() {
    override fun getRequestUrl(): String {
        return "https://api.spotify.com/v1/me/following?type=artist&offset=0&limit=40"
    }

    override suspend fun executeRequest(requestBody: Unit, headers: List<Pair<String, String>>) {
        try {
            val response = httpClient?.get(getRequestUrl()) {
                headers.forEach {
                    header(it.first, it.second)
                }
            }

            if (isSuccessResponse(response?.status ?: HttpStatusCode.BadRequest)) {
                response?.body<SpotifyArtistsResponse>()?.let {
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