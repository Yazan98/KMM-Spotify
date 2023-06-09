package com.yazantarifi.radio.composable.models.items

import com.yazantarifi.radio.composable.models.RadioHomeItem


data class RadioAlbum(
    val image: String,
    val releaseDate: String,
    val numberOfTracks: Int,
    val artists: List<String>,
    val id: String,
    val name: String,
    val loadingMessage: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_ALBUM
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}