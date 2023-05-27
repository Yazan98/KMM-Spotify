package com.yazantarifi.radio.android.home.viewModels

import com.yazantarifi.radio.useCases.GetDiscoverContentUseCase

interface HomeAction {
    object GetFeed: HomeAction
    data class GetDiscoverContent(val key: GetDiscoverContentUseCase.DiscoverKeys, val isHardReload: Boolean): HomeAction
    object GetAccountInfo: HomeAction
}
