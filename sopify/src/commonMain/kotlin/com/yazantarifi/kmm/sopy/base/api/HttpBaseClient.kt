package com.yazantarifi.kmm.sopy.base.api

import io.ktor.client.HttpClient

expect class HttpBaseClient() {
    val httpClient: HttpClient
}