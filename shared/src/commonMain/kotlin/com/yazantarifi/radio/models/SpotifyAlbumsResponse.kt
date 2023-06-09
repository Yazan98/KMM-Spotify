package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAlbumsResponse(
    @SerialName("albums") val albums: SpotifyAlbumsItemsResponse? = null
)
@Serializable
data class SpotifyAlbumsItemsResponse(
    @SerialName("items") val items: List<SpotifyAlbum>? = null
)

@Serializable
data class SpotifyAlbum(
    val id: String? = "",
    val name: String? = "",
    @SerialName("release_date") val releaseDate: String? = "",
    @SerialName("total_tracks") val numberOfTracks: Int? = 0,
    val images: List<SpotifyPlaylistImage>? = null,
    val artists: List<AlbumArtist>? = null,
)

@Serializable
data class AlbumArtist(
     val name: String? = ""
)