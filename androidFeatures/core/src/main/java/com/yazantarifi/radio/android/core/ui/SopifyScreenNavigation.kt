package com.yazantarifi.radio.android.core.ui

import android.content.Context
import android.content.Intent
import android.content.ComponentName

enum class SopifyScreenNavigation constructor(private val screenPath: String) {
    HOME_SCREEN("com.yazantarifi.kmm.home.HomeScreen");

    companion object {

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
    }
}