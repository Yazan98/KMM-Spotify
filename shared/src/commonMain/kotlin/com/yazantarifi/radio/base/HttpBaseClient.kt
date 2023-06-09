package com.yazantarifi.radio.base

import io.ktor.client.HttpClient

expect class HttpBaseClient() {
    val httpClient: HttpClient
}