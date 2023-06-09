package com.yazantarifi.kmm.radio.base.context


class SopifyStorageProvider constructor(private val provider: SopifyStorageKeyValue): CoinaStorageProviderImplementation {
    override fun updateLoggedInUser(newState: Boolean) {
        provider.put(SopifyStorageKeys.IS_LOGGED_IN, newState)
    }

    override fun isUserLoggedIn(): Boolean {
        return provider.getBool(SopifyStorageKeys.IS_LOGGED_IN, false)
    }

    override fun insertAccessToken(newToken: String) {
        provider.put(SopifyStorageKeys.ACCESS_TOKEN, newToken)
    }

    override fun insertRefreshToken(refreshToken: String) {
        provider.put(SopifyStorageKeys.REFRESH_TOKEN, refreshToken)
    }

    override fun getAccessToken(): String {
        return provider.getString(SopifyStorageKeys.ACCESS_TOKEN) ?: ""
    }

    override fun getRefreshToken(): String {
        return provider.getString(SopifyStorageKeys.REFRESH_TOKEN) ?: ""
    }
}

interface CoinaStorageProviderImplementation {
    fun updateLoggedInUser(newState: Boolean)
    fun isUserLoggedIn(): Boolean

    fun insertAccessToken(newToken: String)

    fun insertRefreshToken(refreshToken: String)

    fun getAccessToken(): String

    fun getRefreshToken(): String
}