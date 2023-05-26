package com.yazantarifi.radio.android.core

import com.yazantarifi.kmm.sopy.base.api.HttpBaseClient
import io.ktor.client.HttpClient

object RadioApplicationProps {

    internal var applicationHttpClient: HttpClient? = null

    fun getHttpClientInstance(): HttpClient? {
        if (applicationHttpClient == null) {
            applicationHttpClient = HttpBaseClient().httpClient
        }
        return applicationHttpClient
    }

}
