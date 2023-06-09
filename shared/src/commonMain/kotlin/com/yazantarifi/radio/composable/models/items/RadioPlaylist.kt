package com.yazantarifi.radio.composable.models.items

import com.yazantarifi.radio.composable.models.RadioHomeItem


data class RadioPlaylist(
    val id: String? = "",
    val image: String? = "",
    val name: String? = "",
    val ownerName: String? = "",
    val numberOfTracks: Int? = 0,
    val loadingMessage: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_PLAYLIST
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}