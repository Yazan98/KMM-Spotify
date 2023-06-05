package com.yazantarifi.radio.android.home.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.composables.RadioApplicationLoadingComposable
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeAlbumsComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeCategoriesComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeChangeLayoutComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeHeaderComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeNotificationPermissionComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeOpenSpotifyAppComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomePlaylistsComposable
import com.yazantarifi.radio.core.shared.compose.components.models.HomeAlbumsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeCategoriesItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeNotificationPermissionItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeOpenSpotifyAppItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomePlaylistsItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem

@Composable
fun FeedComposable(viewModel: HomeViewModel) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetFeed(context))
    }

    var selectedListLayoutDesign by remember {
        mutableStateOf(HomeLayoutDesignItem.SCROLL_H)
    }

    if (viewModel.feedLoadingListener.value) {
        RadioApplicationLoadingComposable(RadioApplicationMessages.getMessage("loading_feed"))
    } else {
        HomeLinearContentComposable(viewModel, selectedListLayoutDesign) {
            selectedListLayoutDesign = it
        }
    }
}

@Composable
fun HomeLinearContentComposable(viewModel: HomeViewModel, selectedListLayoutDesign: Int, onChangeClickListener: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.feedContentListener.value) { item ->
            item?.let {
                when (it.getItemViewType()) {
                    RadioHomeItem.TYPE_PLAYLIST -> HomePlaylistsComposable(itemParent = item as HomePlaylistsItem)
                    RadioHomeItem.TYPE_OPEN_SPOTIFY_APP -> HomeOpenSpotifyAppComposable(item as HomeOpenSpotifyAppItem)
                    RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION -> HomeNotificationPermissionComposable(item as HomeNotificationPermissionItem)
                    RadioHomeItem.TYPE_LIST_CATEGORIES -> HomeCategoriesComposable(itemParent = item as HomeCategoriesItem)
                    RadioHomeItem.TYPE_HEADER -> HomeHeaderComposable(item = item as HomeHeaderItem)
                    RadioHomeItem.TYPE_ALBUMS -> HomeAlbumsComposable(itemParent = item as HomeAlbumsItem)
                    RadioHomeItem.TYPE_LAYOUT_DESIGN -> HomeChangeLayoutComposable(selectedListLayoutDesign, item as HomeLayoutDesignItem, onChangeClickListener)
                }
            }
        }
    }
}