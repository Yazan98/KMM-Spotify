package com.yazantarifi.radio.api

import com.yazantarifi.kmm.sopy.api.SopifyOneRequest
import com.yazantarifi.radio.models.SpotifyAuthResponse
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.util.InternalAPI

class SpotifyAuthApiRequest: SopifyOneRequest<String, SpotifyAuthResponse>() {

    @OptIn(InternalAPI::class)
    override suspend fun executeRequest(requestBody: String, headers: List<Pair<String, String>>) {
        try {
            val apiRequestBody = Parameters.build {
                append("grant_type", "authorization_code")
                append("code", requestBody)
                append("redirect_uri", "http://localhost")
                append("client_id", SpotifyAuthManager.CLIENT_ID)
                append("client_secret", SpotifyAuthManager.SECRETE_KEY)
            }

            val response = httpClient?.post(getRequestUrl()) {
                body = FormDataContent(apiRequestBody)
                headers.forEach {
                    header(it.first, it.second)
                }
            }

            if (isSuccessResponse(response?.status ?: HttpStatusCode.BadRequest)) {
                response?.body<SpotifyAuthResponse>()?.let {
                    requestListener?.onSuccess(it)
                }
            } else {
                requestListener?.onError(Throwable(response?.bodyAsText()))
            }
        } catch (ex: Exception) {
            requestListener?.onError(ex)
        }
    }

    override fun getRequestUrl(): String {
        return "https://accounts.spotify.com/api/token"
    }

}
