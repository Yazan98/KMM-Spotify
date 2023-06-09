package com.yazantarifi.radio.composable.composables.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.HomeLayoutDesignItem

@Composable
fun HomeChangeLayoutComposable(
    selectedIconFilter: Int,
    item: HomeLayoutDesignItem,
    onChangeLayoutClickListener: (Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(item.title, color = getTextColor(), fontSize = TextUnit(12f, TextUnitType.Sp))
        Row {
            Icon(Icons.Default.List, contentDescription = "List Icon", modifier = Modifier.size(20.dp).clickable {  onChangeLayoutClickListener(HomeLayoutDesignItem.SCROLL_H) }, tint = if (selectedIconFilter == HomeLayoutDesignItem.SCROLL_H) getTextColor() else getSecondTextColor())
            Icon(Icons.Default.MoreVert, contentDescription = "Grid Icon", modifier = Modifier.size(20.dp).clickable {  onChangeLayoutClickListener(HomeLayoutDesignItem.SCROLL_V) }, tint = if (selectedIconFilter == HomeLayoutDesignItem.SCROLL_V) getTextColor() else getSecondTextColor())
        }
    }
}
