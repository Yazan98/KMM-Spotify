package com.yazantarifi.radio.core.shared.compose.components.composables.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.core.shared.compose.components.composables.getTextColor
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem

@Composable
fun HomeChangeLayoutComposable(item: HomeLayoutDesignItem, onChangeLayoutClickListener: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(item.title, color = getTextColor(), fontSize = TextUnit(12f, TextUnitType.Sp))
        Text("Change Layout", color = getTextColor(), fontSize = TextUnit(12f, TextUnitType.Sp), modifier = Modifier.clickable {
            onChangeLayoutClickListener(HomeLayoutDesignItem.SCROLL_V)
        })
    }
}
