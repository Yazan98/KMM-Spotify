package com.yazantarifi.radio.android.core.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yazantarifi.radio.android.core.RadioTheme

abstract class SopifyScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                RadioTheme {
                    OnScreenContent(savedInstanceState)
                }
            }
        }

        onScreenStarted(savedInstanceState)
    }

    @Composable
    protected fun getBackgroundColor(): Color {
        return when (isSystemInDarkTheme()) {
            true -> Color(0x00000000)
            false -> Color(0x00FFFFFF)
        }
    }

    @Composable
    protected fun getApplicationColor(): Color {
        return Color(0xFF36FFBF)
    }

    @Composable
    fun getTextColor(): Color {
        return MaterialTheme.colorScheme.onBackground
    }

    @Composable
    abstract fun OnScreenContent(savedInstanceState: Bundle?)

    open fun onScreenStarted(savedInstanceState: Bundle?) = Unit

}