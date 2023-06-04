package com.yazantarifi.radio.core.shared.compose.components.models.items

data class RadioPlaylist(
    val id: String? = "",
    val image: String? = "",
    val name: String? = "",
    val ownerName: String? = "",
    val numberOfTracks: Int? = 0
)