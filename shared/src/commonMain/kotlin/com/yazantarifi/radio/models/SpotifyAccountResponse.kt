package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAccountResponse(
    val country: String? = "",
    val id: String? = "",
    @SerialName("display_name") val name: String? = "",
    @SerialName("email") val email: String? = "",
    @SerialName("product") val product: String? = "",
    @SerialName("type") val type: String? = "",
    @SerialName("followers") val followers: AccountFollowers? = null,
    @SerialName("images") val images: List<SpotifyPlaylistImage>? = null,
)

@Serializable
data class AccountFollowers(
    val total: Int? = 0
)
