package com.yazantarifi.radio.android.home.viewModels

import com.yazantarifi.kmm.sopy.base.viewModels.SopifyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): SopifyViewModel<HomeAction>() {

    override suspend fun onNewActionTriggered(action: HomeAction) {
        when (action) {
        }
    }

}