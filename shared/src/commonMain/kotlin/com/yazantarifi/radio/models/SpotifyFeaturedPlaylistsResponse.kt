package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyFeaturedPlaylistsResponse(
    val message: String? = null,
    val sectionName: String? = null,
    val playlists: SpotifyPlaylistsResponse? = null
)

@Serializable
data class SpotifyPlaylistsResponse(
    val items: List<SpotifyPlaylist>? = null
)

@Serializable
data class SpotifyPlaylist(
    val collaborative: Boolean? = false,
    val description: String? = "",
    val id: String? = "",
    val name: String? = "",
    val owner: PlaylistOwner? = null,
    val images: List<SpotifyPlaylistImage>? = null,
    val tracks: SpotifyPlaylistTrack? = null,
    @SerialName("snapshot_id") val snapshotId: String? = ""
)

@Serializable
data class SpotifyPlaylistTrack(
    val total: Int? = 0
)

@Serializable
data class SpotifyPlaylistImage(
    val url: String? = ""
)

@Serializable
data class PlaylistOwner(
    @SerialName("display_name") val name: String? = "",
    val id: String? = "",
    val type: String? = ""
)