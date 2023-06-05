package com.yazantarifi.radio.core.shared.compose.components.models.account

interface RadioAccountItem {
    fun getViewType(): Int

    companion object {
        const val TYPE_ACCOUNT_INFO = 1
        const val TYPE_SECTION = 2
        const val TYPE_LIST_ITEM = 3
        const val TYPE_ARTIST = 4
        const val TYPE_TRACK = 5
    }
}