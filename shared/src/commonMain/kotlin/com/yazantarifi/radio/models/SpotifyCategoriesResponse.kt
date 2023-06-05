package com.yazantarifi.radio.models

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyCategoriesResponse(
    val categories: CategoriesResponse? = null
)

@Serializable
data class CategoriesResponse(
    val items: List<CategoryResponse>? = null
)

@Serializable
data class CategoryResponse(
    val id: String? = "",
    val name: String? = "",
    val icons: List<SpotifyPlaylistImage>? = null,
)