package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyArtistsResponse(
    val artists: ArtistsResponse? = null
)

@Serializable
data class ArtistsResponse(
    val items: List<SpotifyArtist>? = null
)

@Serializable
data class SpotifyArtist(
    val followers: AccountFollowers? = null,
    val genres: List<String>? = null,
    val id: String? = "",
    val name: String? = "",
    val type: String? = "",
    val popularity: Int? = 0,
    @SerialName("images") val images: List<SpotifyPlaylistImage>? = null,
)
