package com.yazantarifi.radio.android.home.viewModels

import android.content.Context


interface HomeAction {
    data class GetFeed(val context: Context): HomeAction
    data class GetDiscoverContent(val isHardReload: Boolean): HomeAction
    object GetAccountInfo: HomeAction
}
