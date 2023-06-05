package com.yazantarifi.radio.core.shared.compose.components.models

import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioAlbum
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioCategoryItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist

data class HomeHeaderItem(
    val title: String,
    val description: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_HEADER
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}

data class HomeSectionHeaderItem(
    val sectionName: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_SECTION_HEADER
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }

}

data class HomeLayoutDesignItem(
    val layout: Int,
    val title: String
): RadioHomeItem {

    companion object {
        const val SCROLL_H = 1
        const val SCROLL_V = 2
    }

    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_LAYOUT_DESIGN
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}

data class HomeCategoriesItem(
    val title: String,
    val loadingMessage: String,
    val items: List<RadioCategoryItem>
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_LIST_CATEGORIES
    }

    override fun isMultiItems(): Boolean {
        return true
    }

    override fun getContentList(): List<RadioHomeItem> {
        return items
    }
}

data class HomeNotificationPermissionItem(
    val isWarningMessageEnabled: Boolean = false,
    val warningMessage: String,
    val title: String,
    val iconUrl: String,
    val message: String,
    val enableButtonText: String,
    val disableButtonText: String,
    val isNotificationsPermissionEnabled: Boolean
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}

data class HomeOpenSpotifyAppItem(
    val title: String,
    val description: String,
    val buttonText: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_OPEN_SPOTIFY_APP
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}

data class HomePlaylistsItem(
    val title: String,
    val loadingMessage: String,
    val playlists: List<RadioPlaylist>? = null
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_LIST_H_PLAYLIST
    }

    override fun isMultiItems(): Boolean {
        return true
    }

    override fun getContentList(): List<RadioHomeItem> {
        return playlists ?: arrayListOf()
    }
}

data class HomeAlbumsItem(
    val title: String,
    val loadingMessage: String,
    val list: List<RadioAlbum>
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_ALBUMS
    }

    override fun isMultiItems(): Boolean {
        return true
    }

    override fun getContentList(): List<RadioHomeItem> {
        return list
    }

}
