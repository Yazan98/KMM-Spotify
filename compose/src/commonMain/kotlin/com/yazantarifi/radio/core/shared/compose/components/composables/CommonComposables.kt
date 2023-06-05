package com.yazantarifi.radio.core.shared.compose.components.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getTextColor(): Color {
    return when (!isSystemInDarkTheme()) {
        true -> Color(0xFF000000)
        false -> Color(0xFFFFFFFF)
    }
}

@Composable
fun getSecondTextColor(): Color {
    return when (!isSystemInDarkTheme()) {
        true -> Color(0xFF575757)
        false -> Color(0xFFDCDCDC)
    }
}

fun getApplicationColor(): Color {
    return Color(0xFF3DDC84)
}