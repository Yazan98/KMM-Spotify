package com.yazantarifi.kmm.sopy.base.api

import com.yazantarifi.kmm.sopy.SopifyNoInternetException
import com.yazantarifi.kmm.sopy.SopifyUnknownException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.UnknownHostException

actual class HttpBaseClient {

    actual val httpClient: HttpClient = HttpClient {
        defaultRequest {
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Type", "application/json")
                append("Accept", "application/json")
            }
        }

        expectSuccess = false
        developmentMode = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            validateResponse { response ->
                when (response.status.value) {
//                    CoinaResponseCode.ERROR_RESPONSE_CODE_NOT_FOUND -> {}
//                    CoinaResponseCode.ERROR_RESPONSE_CODE_REDIRECT -> {}
//                    CoinaResponseCode.ERROR_RESPONSE_CODE_UNAUTHORIZED -> {}
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, request: HttpRequest ->
                when (cause) {
                    is ResponseException -> {
                        throw cause
                    }

                    is UnknownHostException -> {
                        throw SopifyNoInternetException()
                    }

                    else -> {
                        throw SopifyUnknownException(cause)
                    }
                }
            }
        }

    }
}