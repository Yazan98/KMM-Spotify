package com.yazantarifi.kmm.sopy.base.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.set
import kotlinx.serialization.json.Json
import platform.Foundation.NSData
import platform.Foundation.base64EncodedStringWithOptions
import platform.Foundation.create

actual class HttpBaseClient {
    actual val httpClient: HttpClient = HttpClient(Darwin) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }

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
    }
}