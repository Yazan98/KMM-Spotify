package com.yazantarifi.radio.android.core.composables.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen

@Composable
fun ColoredText(message: String) {
    Text(text = message, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun WhiteColoredText(message: String) {
    Text(text = message, color = Color.White)
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TitleText(message: String) {
    Text(text = message, color = MaterialTheme.colorScheme.onBackground, fontSize = TextUnit(16f, TextUnitType.Sp))
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SeconderyTitleText(message: String) {
    Text(text = message, color = SopifyStateScreen.getGreyColor(), fontSize = TextUnit(15f, TextUnitType.Sp))
}