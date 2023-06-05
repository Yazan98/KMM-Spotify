package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyTracksResponse(
    val items: List<SpotifyTrackContainer>? = null
)

@Serializable
data class SpotifyTrackContainer(
    val track: SpotifyTrack? = null
)

@Serializable
data class SpotifyTrack(
    @SerialName("duration_ms") val duration: Long? = 0L,
    val id: String? = "",
    val name: String? = "",
    @SerialName("preview_url") val previewPlayUrl: String? = "",
    val artists: List<SpotifyTrackArtist>? = null,
    val album: SpotifyTrackAlbum? = null
)

@Serializable
data class SpotifyTrackArtist(
    val id: String? = "",
    val name: String? = ""
)

@Serializable
data class SpotifyTrackAlbum(
    val images: List<SpotifyPlaylistImage>? = null,
    val name: String? = "",
    @SerialName("total_tracks") val tracksNumber: Int? = 0
)
