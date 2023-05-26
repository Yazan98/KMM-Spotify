package com.yazantarifi.radio.android.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel

@Composable
fun FeedComposable(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetFeed)
    }
}