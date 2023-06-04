package com.yazantarifi.radio.api

class RedditAuthManager {

    companion object {
        const val CLIENT_ID = "4hZ_IP2WqOXhatcxuw8vow"
        const val CLIENT_SECRETE = "TbO_mLm13pDDMOK9ztyryPVOo0-JlA"
    }

    fun getAuthLoginUrl(): String {
        val state = "random_state_value"
        val redirectUri = "https://github.com/Yazan98/KMM-Reddit"
        val scopes = "identity read"

        return "https://www.reddit.com/api/v1/authorize" +
                "?client_id=$CLIENT_ID" +
                "&response_type=code" +
                "&state=$state" +
                "&redirect_uri=$redirectUri" +
                "&duration=permanent" +
                "&scope=$scopes"
    }

    fun isAccessTokenUrl(url: String): Boolean {
        return url.contains("https://github.com/Yazan98/KMM-Reddit?state=random_state_value&code=")
    }

    fun getAccessTokenByUrl(url: String): String {
        return url.replace("https://github.com/Yazan98/KMM-Reddit?state=random_state_value&code=", "")
    }

}
