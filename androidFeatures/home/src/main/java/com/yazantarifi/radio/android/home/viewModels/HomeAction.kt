package com.yazantarifi.radio.android.home.viewModels


interface HomeAction {
    object GetFeed: HomeAction
    data class GetDiscoverContent(val isHardReload: Boolean): HomeAction
    object GetAccountInfo: HomeAction
}
