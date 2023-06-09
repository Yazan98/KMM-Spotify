package com.yazantarifi.radio

import androidx.compose.ui.window.ComposeUIViewController
import com.yazantarifi.radio.composable.composables.account.AccountArtistComposable
import com.yazantarifi.radio.composable.composables.account.AccountInfoComposable
import com.yazantarifi.radio.composable.composables.account.AccountSectionComposable
import com.yazantarifi.radio.composable.composables.account.AccountSectionValueComposable
import com.yazantarifi.radio.composable.composables.account.AccountTrackComposable
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
import com.yazantarifi.radio.composable.composables.playlists.PlaylistItemComposable
import com.yazantarifi.radio.composable.models.HomeAlbumsItem
import com.yazantarifi.radio.composable.models.HomeCategoriesItem
import com.yazantarifi.radio.composable.models.HomeHeaderItem
import com.yazantarifi.radio.composable.models.HomeLayoutDesignItem
import com.yazantarifi.radio.composable.models.HomeNotificationPermissionItem
import com.yazantarifi.radio.composable.models.HomeOpenSpotifyAppItem
import com.yazantarifi.radio.composable.models.HomePlaylistsItem
import com.yazantarifi.radio.composable.models.HomeSectionHeaderItem
import com.yazantarifi.radio.composable.models.account.AccountArtistItem
import com.yazantarifi.radio.composable.models.account.AccountHeaderItem
import com.yazantarifi.radio.composable.models.account.AccountListSectionItem
import com.yazantarifi.radio.composable.models.account.AccountSectionNameItem
import com.yazantarifi.radio.composable.models.account.AccountTrackItem
import com.yazantarifi.radio.composable.models.items.RadioAlbum
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
import com.yazantarifi.radio.composable.models.items.RadioPlaylist
import platform.UIKit.UIViewController

fun HomeAlbumComposableAlias(item: RadioAlbum): UIViewController = ComposeUIViewController {
    HomeAlbumComposable(item)
}

fun HomeAlbumsComposableAlias(item: HomeAlbumsItem): UIViewController = ComposeUIViewController {
    HomeAlbumsComposable(item)
}

fun HomeCategoriesComposableAlias(itemParent: HomeCategoriesItem, onCategoryClickListener: (String) -> Unit): UIViewController = ComposeUIViewController {
    HomeCategoriesComposable(itemParent, onCategoryClickListener)
}

fun HomeCategoryComposableAlias(item: RadioCategoryItem, onCategoryClickListener: (String) -> Unit): UIViewController = ComposeUIViewController {
    HomeCategoryComposable(item, onCategoryClickListener)
}

fun HomeChangeLayoutComposableAlias(
    selectedIconFilter: Int,
    item: HomeLayoutDesignItem,
    onChangeLayoutClickListener: (Int) -> Unit
): UIViewController = ComposeUIViewController {
    HomeChangeLayoutComposable(selectedIconFilter, item, onChangeLayoutClickListener)
}

fun HomeHeaderComposableAlias(item: HomeHeaderItem): UIViewController = ComposeUIViewController {
    HomeHeaderComposable(item)
}

fun HomeNotificationPermissionComposableAlias(item: HomeNotificationPermissionItem, onItemClickListener: (Boolean) -> Unit): UIViewController = ComposeUIViewController {
    HomeNotificationPermissionComposable(item, onItemClickListener)
}

fun HomeOpenSpotifyAppComposableAlias(item: HomeOpenSpotifyAppItem, onButtonClickListener: () -> Unit): UIViewController = ComposeUIViewController {
    HomeOpenSpotifyAppComposable(item, onButtonClickListener)
}

fun HomePlaylistComposableAlias(item: RadioPlaylist): UIViewController = ComposeUIViewController {
    HomePlaylistComposable(item)
}

fun HomePlaylistsComposableAlias(itemParent: HomePlaylistsItem): UIViewController = ComposeUIViewController {
    HomePlaylistsComposable(itemParent)
}

fun HomeSectionHeaderComposableAlias(item: HomeSectionHeaderItem): UIViewController = ComposeUIViewController {
    HomeSectionHeaderComposable(item)
}

fun PlaylistItemComposableAlias(item: RadioPlaylist): UIViewController = ComposeUIViewController {
    PlaylistItemComposable(item)
}

fun AccountArtistComposableAlias(item: AccountArtistItem) : UIViewController = ComposeUIViewController {
    AccountArtistComposable(item)
}

fun AccountInfoComposableAlias(item: AccountHeaderItem): UIViewController = ComposeUIViewController {
    AccountInfoComposable(item)
}

fun AccountSectionComposableAlias(item: AccountSectionNameItem): UIViewController = ComposeUIViewController {
    AccountSectionComposable(item)
}

fun AccountSectionValueComposableAlias(item: AccountListSectionItem): UIViewController = ComposeUIViewController {
    AccountSectionValueComposable(item)
}

fun AccountTrackComposableAlias(item: AccountTrackItem): UIViewController = ComposeUIViewController {
    AccountTrackComposable(item)
}