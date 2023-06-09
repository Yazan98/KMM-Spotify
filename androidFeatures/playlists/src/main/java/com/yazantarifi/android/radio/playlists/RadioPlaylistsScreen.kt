package com.yazantarifi.android.radio.playlists

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yazantarifi.android.radio.playlists.viewModel.PlaylistAction
import com.yazantarifi.android.radio.playlists.viewModel.PlaylistsViewModel
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.composables.RadioApplicationLoadingComposable
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.android.core.ui.SopifyScreenNavigation
import com.yazantarifi.radio.composable.composables.playlists.PlaylistItemComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioPlaylistsScreen: SopifyStateScreen<PlaylistAction, PlaylistsViewModel>() {

    @Composable
    override fun onScreenContent(savedInstanceState: Bundle?): PlaylistsViewModel {
        val viewModel: PlaylistsViewModel = hiltViewModel()
        val categoryId = intent.extras?.getString(SopifyScreenNavigation.PLAY_LIST_ARGS, "") ?: ""
        if (categoryId.isEmpty()) {
            finish()
            return viewModel
        }

        LaunchedEffect(true) {
            viewModel.execute(PlaylistAction.GetPlaylistsByCategoryIdAction(categoryId))
        }

        if (viewModel.loadingListener.value) {
            RadioApplicationLoadingComposable(RadioApplicationMessages.getMessage("loading_playlists"))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.screenContentListener.value) { index, item ->
                    if (index >= viewModel.screenContentListener.value.size - 7) {
                        viewModel.execute(PlaylistAction.GetPaginationAction)
                    }
                    PlaylistItemComposable(item)
                }
            }
        }

        return viewModel
    }

    override fun isDefaultToolbarEnabled(): Boolean {
        return true
    }

}
