package com.yazantarifi.radio.android.home.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.composables.RadioApplicationLoadingComposable
import com.yazantarifi.radio.android.core.composables.screens.ColoredText
import com.yazantarifi.radio.android.core.composables.screens.PostListingComposable
import com.yazantarifi.radio.android.core.composables.screens.WhiteColoredText
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import com.yazantarifi.radio.useCases.GetDiscoverContentUseCase

@Composable
fun DiscoverComposable(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetDiscoverContent(GetDiscoverContentUseCase.DiscoverKeys.HOT, false))
    }

    val selectedPosition = remember { viewModel.discoverFilterSelectedPosition }
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedButton(
                onClick = {
                    selectedPosition.value = 0
                    viewModel.execute(HomeAction.GetDiscoverContent(GetDiscoverContentUseCase.DiscoverKeys.HOT, true))
                },
                shape = RoundedCornerShape(50), // = 50% percent
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = if (selectedPosition.value == 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.weight(1f)
            ){
                ColoredText(message = RadioApplicationMessages.getMessage("hot"))
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedButton(
                onClick = {
                    selectedPosition.value = 1
                    viewModel.execute(HomeAction.GetDiscoverContent(GetDiscoverContentUseCase.DiscoverKeys.NEW, true))
                },
                shape = RoundedCornerShape(50), // = 50% percent
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = if (selectedPosition.value == 1) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.weight(1f)
            ){
                ColoredText(message = RadioApplicationMessages.getMessage("new"))
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedButton(
                onClick = {
                    selectedPosition.value = 2
                    viewModel.execute(HomeAction.GetDiscoverContent(GetDiscoverContentUseCase.DiscoverKeys.TOP, true))
                },
                shape = RoundedCornerShape(50), // = 50% percent
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = if (selectedPosition.value == 2) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.weight(1f)
            ){
                ColoredText(message = RadioApplicationMessages.getMessage("top"))
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
        Spacer(modifier = Modifier.width(10.dp))
        if (viewModel.discoverLoadingListener.value) {
            RadioApplicationLoadingComposable(message = RadioApplicationMessages.getMessage("loading_discover:${selectedPosition.value}"))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.discoverContentListener.value) { item ->
                    item?.let {
                        PostListingComposable(post = it)
                    }
                }
            }
        }
    }
}
