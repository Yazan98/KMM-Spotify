package com.yazantarifi.radio.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.kmm.sopy.base.useCases.SopifyState
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseListener
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseType
import com.yazantarifi.kmm.sopy.base.viewModels.SopifyViewModel
import com.yazantarifi.radio.models.RedditFeedPayload
import com.yazantarifi.radio.models.RedditFeedPost
import com.yazantarifi.radio.useCases.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val feedUseCase: GetFeedUseCase,
    private val storageProvider: SopifyStorageProvider
): SopifyViewModel<HomeAction>() {

    val feedLoadingListener by lazy { mutableStateOf(false) }
    val feedContentListener: MutableState<ArrayList<RedditFeedPost?>> by lazy { mutableStateOf(arrayListOf()) }

    val discoverLoadingListener by lazy { mutableStateOf(false) }
    val accountLoadingListener by lazy { mutableStateOf(false) }

    override suspend fun onNewActionTriggered(action: HomeAction) {
        when (action) {
            is HomeAction.GetFeed -> onGetHomeScreenFeedInfo()
        }
    }

    private fun onGetHomeScreenFeedInfo() {
        feedUseCase.execute(
            GetFeedUseCase.RequestValue("", storageProvider.getAccessToken()),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> feedLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? RedditFeedPayload)?.let {
                                if (it.isHardReload) {
                                    feedContentListener.value.clear()
                                }

                                it.posts?.let { it1 -> feedContentListener.value.addAll(it1) }
                            }
                        }
                    }
                }
            }
        )
    }

    override fun getSupportedUseCases(): ArrayList<SopifyUseCaseType> {
        return arrayListOf(feedUseCase)
    }

}
