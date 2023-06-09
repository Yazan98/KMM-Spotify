package com.yazantarifi.radio.android.home.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.composables.RadioApplicationLoadingComposable
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import com.yazantarifi.radio.composable.composables.account.AccountArtistComposable
import com.yazantarifi.radio.composable.composables.account.AccountInfoComposable
import com.yazantarifi.radio.composable.composables.account.AccountSectionComposable
import com.yazantarifi.radio.composable.composables.account.AccountSectionValueComposable
import com.yazantarifi.radio.composable.composables.account.AccountTrackComposable
import com.yazantarifi.radio.composable.models.account.AccountArtistItem
import com.yazantarifi.radio.composable.models.account.AccountHeaderItem
import com.yazantarifi.radio.composable.models.account.AccountListSectionItem
import com.yazantarifi.radio.composable.models.account.AccountSectionNameItem
import com.yazantarifi.radio.composable.models.account.AccountTrackItem
import com.yazantarifi.radio.composable.models.account.RadioAccountItem

@Composable
fun AccountComposable(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetAccountInfoAction)
    }

    if (viewModel.accountLoadingListener.value) {
        RadioApplicationLoadingComposable(RadioApplicationMessages.getMessage("loading_account"))
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.accountInfoListListener.value) { item ->
                item?.let {
                    when (it.getViewType()) {
                        RadioAccountItem.TYPE_ACCOUNT_INFO -> AccountInfoComposable(item = it as AccountHeaderItem)
                        RadioAccountItem.TYPE_SECTION -> AccountSectionComposable(item = it as AccountSectionNameItem)
                        RadioAccountItem.TYPE_LIST_ITEM -> AccountSectionValueComposable(item = it as AccountListSectionItem)
                        RadioAccountItem.TYPE_ARTIST -> AccountArtistComposable(item = it as AccountArtistItem)
                        RadioAccountItem.TYPE_TRACK -> AccountTrackComposable(item = it as AccountTrackItem)
                    }
                }
            }
        }
    }
}