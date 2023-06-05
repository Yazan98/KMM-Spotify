package com.yazantarifi.radio.core.shared.compose.components.models.items

data class RadioAlbum(
    val image: String,
    val releaseDate: String,
    val numberOfTracks: Int,
    val artists: List<String>,
    val id: String,
    val name: String,
)