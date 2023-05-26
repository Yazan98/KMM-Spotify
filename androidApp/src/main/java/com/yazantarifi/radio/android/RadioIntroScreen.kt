package com.yazantarifi.radio.android

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.radio.android.auth.RadioAuthScreen
import com.yazantarifi.radio.android.core.screens.SopifyScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RadioIntroScreen : SopifyScreen() {

    @Inject
    lateinit var storage: SopifyStorageProvider

    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) = Unit

    override fun onScreenStarted(savedInstanceState: Bundle?) {
        super.onScreenStarted(savedInstanceState)
        when (storage.isUserLoggedIn()) {
            true -> {
//                HomeScreen.startScreen(this)
            }
            false -> RadioAuthScreen.startScreen(this)
        }
    }

}