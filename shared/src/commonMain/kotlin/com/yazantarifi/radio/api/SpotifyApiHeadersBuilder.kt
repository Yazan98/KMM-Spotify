package com.yazantarifi.radio.api

object SpotifyApiHeadersBuilder {
    fun getApplicationAuthHeader(): List<Pair<String, String>> {
        return arrayListOf(
            "Authorization" to "Basic MWZjYTE1MDIyMDhlNDRhNWI3YmU4YWE5MTliYTU4YmM6YzBhMzM2Y2JlODY0NDNiYWE1MDFmYmEwOGVlNDMxMmY="
        )
    }
}