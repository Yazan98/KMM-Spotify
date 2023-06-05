package com.yazantarifi.radio.android.home.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.composables.RadioApplicationLoadingComposable
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import com.yazantarifi.radio.core.shared.compose.components.composables.home.HomeCategoryComposable

@Composable
fun CategoriesComposable(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.execute(HomeAction.GetCategoriesAction)
    }

    if (viewModel.categoriesLoadingListener.value) {
        RadioApplicationLoadingComposable(message = RadioApplicationMessages.getMessage("loading_categories"))
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
            items(viewModel.categoriesListListener.value) { item ->
                item?.let {
                    HomeCategoryComposable(item = item)
                }
            }
        }
    }
}
