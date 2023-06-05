package com.yazantarifi.radio.android.core.ui

import android.content.Context
import android.content.Intent
import android.content.ComponentName

enum class SopifyScreenNavigation constructor(private val screenPath: String) {
    HOME_SCREEN("com.yazantarifi.radio.android.home.RadioHomeScreen"),
    PLAYLISTS_SCREEN("com.yazantarifi.android.radio.playlists.RadioPlaylistsScreen");

    companion object {

        const val PLAY_LIST_ARGS = "args.playlist.id"

        fun getIntent(context: Context, screenNavigation: SopifyScreenNavigation): Intent {
            return Intent().apply {
                component = ComponentName(context.packageName, screenNavigation.screenPath)
            }
        }

        fun startScreen(context: Context, screenNavigation: SopifyScreenNavigation) {
            Intent().apply {
                component = ComponentName(context.packageName, screenNavigation.screenPath)
                context.startActivity(this)
            }
        }

        fun startScreenByArgs(context: Context, screenNavigation: SopifyScreenNavigation): Intent {
            return Intent().apply {
                component = ComponentName(context.packageName, screenNavigation.screenPath)
            }
        }
    }
}