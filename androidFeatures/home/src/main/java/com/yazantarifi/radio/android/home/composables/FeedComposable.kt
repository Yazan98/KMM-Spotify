package com.yazantarifi.radio.android.home.composables

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.yazantarifi.radio.android.core.ui.SopifyScreenNavigation
import com.yazantarifi.radio.android.home.RadioHomeScreen
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import com.yazantarifi.radio.composable.composables.home.HomeAlbumComposable
import com.yazantarifi.radio.composable.composables.home.HomeAlbumsComposable
import com.yazantarifi.radio.composable.composables.home.HomeCategoriesComposable
import com.yazantarifi.radio.composable.composables.home.HomeCategoryComposable
import com.yazantarifi.radio.composable.composables.home.HomeChangeLayoutComposable
import com.yazantarifi.radio.composable.composables.home.HomeHeaderComposable
import com.yazantarifi.radio.composable.composables.home.HomeNotificationPermissionComposable
import com.yazantarifi.radio.composable.composables.home.HomeOpenSpotifyAppComposable
import com.yazantarifi.radio.composable.composables.home.HomePlaylistComposable
import com.yazantarifi.radio.composable.composables.home.HomePlaylistsComposable
import com.yazantarifi.radio.composable.composables.home.HomeSectionHeaderComposable
import com.yazantarifi.radio.composable.models.HomeAlbumsItem
import com.yazantarifi.radio.composable.models.HomeCategoriesItem
import com.yazantarifi.radio.composable.models.HomeHeaderItem
import com.yazantarifi.radio.composable.models.HomeLayoutDesignItem
import com.yazantarifi.radio.composable.models.HomeNotificationPermissionItem
import com.yazantarifi.radio.composable.models.HomeOpenSpotifyAppItem
import com.yazantarifi.radio.composable.models.HomePlaylistsItem
import com.yazantarifi.radio.composable.models.HomeSectionHeaderItem
import com.yazantarifi.radio.composable.models.RadioHomeItem
import com.yazantarifi.radio.composable.models.items.RadioAlbum
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
import com.yazantarifi.radio.composable.models.items.RadioPlaylist
import com.yazantarifi.radio.mappers.HomeScreenVerticalGridMapper
import kotlinx.coroutines.flow.update

@Composable
fun FeedComposable(viewModel: HomeViewModel, isNotificationPermissionEnabledListener: Boolean) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetFeed(context))
    }

    if (isNotificationPermissionEnabledListener) {
        viewModel.execute(HomeAction.RemoveNotificationPermissionAction)
    }

    var selectedListLayoutDesign by remember {
        mutableStateOf(viewModel.selectedLayoutDesignMode)
    }

    if (viewModel.feedLoadingListener.value) {
        RadioApplicationLoadingComposable(RadioApplicationMessages.getMessage("loading_feed"))
    } else {
        when (selectedListLayoutDesign == HomeLayoutDesignItem.SCROLL_H) {
            true -> HomeLinearContentComposable(viewModel, selectedListLayoutDesign) {
                selectedListLayoutDesign = it
                viewModel.selectedLayoutDesignMode = it
            }
            
            false -> HomeGridContentComposable(viewModel, selectedListLayoutDesign) {
                selectedListLayoutDesign = it
                viewModel.selectedLayoutDesignMode = it
            }
        }
    }
}

@Composable
fun HomeGridContentComposable(viewModel: HomeViewModel, selectedListLayoutDesign: Int, onChangeClickListener: (Int) -> Unit) {
    val context = LocalContext.current
    val filteredList by remember {
        mutableStateOf(HomeScreenVerticalGridMapper().getVerticalItems(viewModel.feedContentListener.toList()))
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
                    RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION -> HomeNotificationPermissionComposable(itemContent as HomeNotificationPermissionItem) {
                        onNotificationPermissionClickListener(it, viewModel, context)
                    }
                    RadioHomeItem.TYPE_OPEN_SPOTIFY_APP -> HomeOpenSpotifyAppComposable(itemContent as HomeOpenSpotifyAppItem) {
                        openSpotifyApplication(context, viewModel)
                    }
                    RadioHomeItem.TYPE_CATEGORY -> HomeCategoryComposable(itemContent as RadioCategoryItem) {
                        context.startActivity(SopifyScreenNavigation.startScreenByArgs(context, SopifyScreenNavigation.PLAYLISTS_SCREEN).apply {
                            putExtra(SopifyScreenNavigation.PLAY_LIST_ARGS, it)
                        })
                    }
                    RadioHomeItem.TYPE_PLAYLIST -> HomePlaylistComposable(itemContent as RadioPlaylist)
                    RadioHomeItem.TYPE_ALBUM -> HomeAlbumComposable(itemContent as RadioAlbum)
                }
            }
        }
    }
}

@Composable
fun HomeLinearContentComposable(viewModel: HomeViewModel, selectedListLayoutDesign: Int, onChangeClickListener: (Int) -> Unit) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.feedContentListener) { item ->
            item?.let {
                when (it.getItemViewType()) {
                    RadioHomeItem.TYPE_LIST_H_PLAYLIST -> HomePlaylistsComposable(itemParent = item as HomePlaylistsItem)
                    RadioHomeItem.TYPE_OPEN_SPOTIFY_APP -> HomeOpenSpotifyAppComposable(item as HomeOpenSpotifyAppItem) {
                        openSpotifyApplication(context, viewModel)
                    }
                    RadioHomeItem.TYPE_NOTIFICATIONS_PERMISSION -> HomeNotificationPermissionComposable(item as HomeNotificationPermissionItem) {
                        onNotificationPermissionClickListener(it, viewModel, context)
                    }
                    RadioHomeItem.TYPE_LIST_CATEGORIES -> HomeCategoriesComposable(itemParent = item as HomeCategoriesItem) {
                        context.startActivity(SopifyScreenNavigation.startScreenByArgs(context, SopifyScreenNavigation.PLAYLISTS_SCREEN).apply {
                            putExtra(SopifyScreenNavigation.PLAY_LIST_ARGS, it)
                        })
                    }
                    RadioHomeItem.TYPE_HEADER -> HomeHeaderComposable(item = item as HomeHeaderItem)
                    RadioHomeItem.TYPE_ALBUMS -> HomeAlbumsComposable(itemParent = item as HomeAlbumsItem)
                    RadioHomeItem.TYPE_LAYOUT_DESIGN -> HomeChangeLayoutComposable(selectedListLayoutDesign, item as HomeLayoutDesignItem, onChangeClickListener)
                }
            }
        }
    }
}

private fun openSpotifyApplication(context: Context, viewModel: HomeViewModel) {
    val spotifyPackageName = "com.spotify.music"
    val spotifyIntent = context.packageManager.getLaunchIntentForPackage(spotifyPackageName)

    if (spotifyIntent != null) {
        context.startActivity(spotifyIntent)
    } else {
        // Spotify app is not installed on the device
        viewModel.errorMessageListener.update { RadioApplicationMessages.getMessage("spotify_not_installed") }
    }
}

private fun onNotificationPermissionClickListener(isEnableButton: Boolean, viewModel: HomeViewModel, context: Context) {
    when (isEnableButton) {
        false -> viewModel.execute(HomeAction.RemoveNotificationPermissionAction)
        true -> {
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            if (notificationManager.areNotificationsEnabled()) {
                // Notification permission already granted
                viewModel.execute(HomeAction.RemoveNotificationPermissionAction)
            } else {
                // Request notification permission
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri

                (context as? RadioHomeScreen)?.executePermission(intent)

            }
        }
    }
}