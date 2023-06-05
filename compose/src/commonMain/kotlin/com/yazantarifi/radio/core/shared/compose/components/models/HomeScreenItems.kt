package com.yazantarifi.radio.core.shared.compose.components.models

import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioAlbum
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioCategoryItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioMusicItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist

data class HomeHeaderItem(
    val title: String,
    val description: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_HEADER
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
}

data class HomeMusicItem(
    val title: String,
    val items: List<RadioMusicItem>
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_MUSIC_LIST_SCROLL
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
}

data class HomeOpenSpotifyAppItem(
    val title: String,
    val description: String,
    val buttonText: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_OPEN_SPOTIFY_APP
    }
}

data class HomePlaylistsItem(
    val title: String,
    val loadingMessage: String,
    val playlists: List<RadioPlaylist>? = null
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_PLAYLIST
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
}