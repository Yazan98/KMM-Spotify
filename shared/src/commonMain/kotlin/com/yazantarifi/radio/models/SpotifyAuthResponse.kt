package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAuthResponse(
    @SerialName("access_token") val accessToken: String? = "",
    @SerialName("token_type") val tokenType: String? = "",
    @SerialName("scope") val scope: String? = "",
    @SerialName("expires_in") val expiresIn: Long? = 0L
)