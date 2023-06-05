package com.yazantarifi.radio.android.home.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeAlbumComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeAlbumsComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeCategoriesComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeCategoryComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeChangeLayoutComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeHeaderComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeNotificationPermissionComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeOpenSpotifyAppComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomePlaylistComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomePlaylistsComposable
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeSectionHeaderComposable
import com.yazantarifi.radio.core.shared.compose.components.models.HomeAlbumsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeCategoriesItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeNotificationPermissionItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeOpenSpotifyAppItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomePlaylistsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeSectionHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioAlbum
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioCategoryItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist
import com.yazantarifi.radio.mappers.HomeScreenVerticalGridMapper

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
        when (selectedListLayoutDesign == HomeLayoutDesignItem.SCROLL_H) {
            true -> HomeLinearContentComposable(viewModel, selectedListLayoutDesign) {
                selectedListLayoutDesign = it
            }
            
            false -> HomeGridContentComposable(viewModel, selectedListLayoutDesign) {
                selectedListLayoutDesign = it
            }
        }
    }
}

@Composable
fun HomeGridContentComposable(viewModel: HomeViewModel, selectedListLayoutDesign: Int, onChangeClickListener: (Int) -> Unit) {
    val filteredList by remember {
        mutableStateOf(HomeScreenVerticalGridMapper().getVerticalItems(viewModel.feedContentListener.value))
    }

    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
        filteredList.forEach { itemContent ->
        val isFillScreenWidth = itemContent.getItemViewType() == RadioHomeItem.TYPE_HEADER  || itemContent.getItemViewType() == RadioHomeItem.TYPE_LAYOUT_DESIGN || itemContent.getItemViewType() == RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION || itemContent.getItemViewType() == RadioHomeItem.TYPE_OPEN_SPOTIFY_APP || itemContent.getItemViewType() == RadioHomeItem.TYPE_SECTION_HEADER
            val spanCount = if (isFillScreenWidth) GridItemSpan(3) else GridItemSpan(1)
            item(span = { spanCount }) {
                when (itemContent.getItemViewType()) {
                    RadioHomeItem.TYPE_SECTION_HEADER -> HomeSectionHeaderComposable(item = itemContent as HomeSectionHeaderItem)
                    RadioHomeItem.TYPE_HEADER -> HomeHeaderComposable(item = itemContent as HomeHeaderItem)
                    RadioHomeItem.TYPE_LAYOUT_DESIGN -> HomeChangeLayoutComposable(selectedListLayoutDesign, itemContent as HomeLayoutDesignItem, onChangeClickListener)
                    RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION -> HomeNotificationPermissionComposable(itemContent as HomeNotificationPermissionItem)
                    RadioHomeItem.TYPE_OPEN_SPOTIFY_APP -> HomeOpenSpotifyAppComposable(itemContent as HomeOpenSpotifyAppItem)
                    RadioHomeItem.TYPE_CATEGORY -> HomeCategoryComposable(itemContent as RadioCategoryItem)
                    RadioHomeItem.TYPE_PLAYLIST -> HomePlaylistComposable(itemContent as RadioPlaylist)
                    RadioHomeItem.TYPE_ALBUM -> HomeAlbumComposable(itemContent as RadioAlbum)
                }
            }
        }
    }
}

@Composable
fun HomeLinearContentComposable(viewModel: HomeViewModel, selectedListLayoutDesign: Int, onChangeClickListener: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.feedContentListener.value) { item ->
            item?.let {
                when (it.getItemViewType()) {
                    RadioHomeItem.TYPE_LIST_H_PLAYLIST -> HomePlaylistsComposable(itemParent = item as HomePlaylistsItem)
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