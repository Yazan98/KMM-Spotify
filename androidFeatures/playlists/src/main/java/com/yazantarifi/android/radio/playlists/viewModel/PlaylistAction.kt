package com.yazantarifi.android.radio.playlists.viewModel

interface PlaylistAction {
    data class GetPlaylistsByCategoryIdAction(val id: String): PlaylistAction
    object GetPaginationAction: PlaylistAction
}