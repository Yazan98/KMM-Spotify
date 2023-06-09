package com.yazantarifi.radio.composable.models

interface RadioHomeItem {

    fun getItemViewType(): Int

    fun isMultiItems(): Boolean

    fun getContentList(): List<RadioHomeItem>

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_LAYOUT_DESIGN = 2
        const val TYPE_NOTIFICATIONS_PERMISSION = 5
        const val TYPE_OPEN_SPOTIFY_APP = 6
        const val TYPE_LIST_CATEGORIES = 7
        const val TYPE_LIST_H_PLAYLIST = 8
        const val TYPE_ALBUMS = 9

        const val TYPE_ALBUM = 10
        const val TYPE_PLAYLIST = 11
        const val TYPE_CATEGORY = 12
        const val TYPE_SECTION_HEADER = 13
    }
}

