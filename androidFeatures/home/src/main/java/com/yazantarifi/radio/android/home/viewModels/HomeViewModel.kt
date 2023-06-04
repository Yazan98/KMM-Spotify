package com.yazantarifi.radio.android.home.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.kmm.sopy.base.useCases.SopifyState
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseListener
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseType
import com.yazantarifi.kmm.sopy.base.viewModels.SopifyViewModel
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem
import com.yazantarifi.radio.models.RedditFeedPayload
import com.yazantarifi.radio.models.RedditFeedPost
import com.yazantarifi.radio.useCases.GetDiscoverContentUseCase
import com.yazantarifi.radio.useCases.GetHomeScreenItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val discoverContentUseCase: GetDiscoverContentUseCase,
    private val getHomeScreenItems: GetHomeScreenItemsUseCase,
    private val storageProvider: SopifyStorageProvider
): SopifyViewModel<HomeAction>() {

    val feedLoadingListener by lazy { mutableStateOf(false) }
    val feedContentListener: MutableState<ArrayList<RadioHomeItem?>> by lazy { mutableStateOf(arrayListOf()) }
    val discoverContentListener: MutableState<ArrayList<RedditFeedPost?>> by lazy { mutableStateOf(arrayListOf()) }

    val discoverFilterSelectedPosition by lazy { mutableStateOf(0) }
    val discoverLoadingListener by lazy { mutableStateOf(false) }

    val accountLoadingListener by lazy { mutableStateOf(false) }

    override suspend fun onNewActionTriggered(action: HomeAction) {
        when (action) {
            is HomeAction.GetFeed -> onGetHomeScreenFeedInfo()
            is HomeAction.GetDiscoverContent -> onGetDiscoverInfo(action.key, action.isHardReload)
        }
    }

    private fun onGetDiscoverInfo(action: GetDiscoverContentUseCase.DiscoverKeys, isHardReload: Boolean) {
        if (!isHardReload && discoverContentListener.value.isNotEmpty()) return
        discoverContentUseCase.execute(
            GetDiscoverContentUseCase.RequestParams(storageProvider.getAccessToken(), action),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> discoverLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? RedditFeedPayload)?.let {
                                if (it.isHardReload) {
                                    discoverContentListener.value.clear()
                                }

                                it.posts?.let { it1 -> discoverContentListener.value.addAll(it1) }
                            }
                        }
                    }
                }
            }
        )
    }

    private fun onGetHomeScreenFeedInfo() {
        if (feedContentListener.value.isNotEmpty()) return
        getHomeScreenItems.execute(
            GetHomeScreenItemsUseCase.RequestValue(storageProvider.getAccessToken()),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> feedLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? List<RadioHomeItem>)?.let {
                                feedContentListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    override fun getSupportedUseCases(): ArrayList<SopifyUseCaseType> {
        return arrayListOf(getHomeScreenItems, discoverContentUseCase)
    }

}
