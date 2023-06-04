package com.yazantarifi.radio.core.shared.compose.components.models

interface RadioHomeItem {

    fun getItemViewType(): Int

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_LAYOUT_DESIGN = 2
        const val TYPE_MUSIC_LIST_SCROLL = 3
        const val TYPE_NOTIFICATIONS_PERMISSION = 5
        const val TYPE_RATE_APP = 6
        const val TYPE_LIST_CATEGORIES = 7
        const val TYPE_PLAYLIST = 8
    }
}

