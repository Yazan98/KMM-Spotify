package com.yazantarifi.radio.api

class SpotifyAuthManager {

    companion object {
        const val CLIENT_ID = "1fca1502208e44a5b7be8aa919ba58bc"
        const val SECRETE_KEY = "c0a336cbe86443baa501fba08ee4312f"
    }

    fun getAuthLoginUrl(): String {
        val scopeList = "user-read-private,user-read-email,user-follow-read,playlist-read-private,playlist-read-collaborative"
        return "https://accounts.spotify.com/authorize" +
                "?client_id=$CLIENT_ID" +
                "&response_type=code" +
                "&redirect_uri=http://localhost" +
                "&scope=$scopeList" +
                "&state=Yazan98 Random String Here"
    }

    fun isAccessTokenUrl(url: String): Boolean {
        return url.contains("http://localhost/?code=")
    }

    fun getAccessTokenByUrl(url: String): String {
        val codeStart = url.replace("http://localhost/?code=", "")
        return codeStart.split("&state=")[0]
    }

}
