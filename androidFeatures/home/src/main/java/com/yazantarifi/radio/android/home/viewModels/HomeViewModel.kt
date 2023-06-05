package com.yazantarifi.radio.android.home.viewModels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationManagerCompat
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.kmm.sopy.base.useCases.SopifyState
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseListener
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseType
import com.yazantarifi.kmm.sopy.base.viewModels.SopifyViewModel
import com.yazantarifi.radio.core.shared.compose.components.models.account.RadioAccountItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeLayoutDesignItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioCategoryItem
import com.yazantarifi.radio.useCases.GetAccountTreeInfoUseCase
import com.yazantarifi.radio.useCases.GetCategoriesUseCase
import com.yazantarifi.radio.useCases.GetHomeScreenItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeScreenItems: GetHomeScreenItemsUseCase,
    private val categoriesUseCase: GetCategoriesUseCase,
    private val accountInfoUseCase: GetAccountTreeInfoUseCase,
    private val storageProvider: SopifyStorageProvider
): SopifyViewModel<HomeAction>() {

    var selectedLayoutDesignMode: Int = HomeLayoutDesignItem.SCROLL_H
    val feedLoadingListener by lazy { mutableStateOf(false) }
    val feedContentListener: MutableState<ArrayList<RadioHomeItem?>> by lazy { mutableStateOf(arrayListOf()) }

    val categoriesListListener: MutableState<ArrayList<RadioCategoryItem?>> by lazy { mutableStateOf(arrayListOf()) }
    val categoriesLoadingListener by lazy { mutableStateOf(false) }

    val accountLoadingListener by lazy { mutableStateOf(false) }
    val accountInfoListListener: MutableState<ArrayList<RadioAccountItem>> by lazy { mutableStateOf(arrayListOf()) }

    override suspend fun onNewActionTriggered(action: HomeAction) {
        when (action) {
            is HomeAction.GetFeed -> onGetHomeScreenFeedInfo(action.context)
            is HomeAction.GetCategoriesAction -> onGetCategories()
            is HomeAction.GetAccountInfoAction -> onGetAccountInfo()
        }
    }

    private fun onGetCategories() {
        if (categoriesListListener.value.isNotEmpty()) return
        categoriesUseCase.execute(
            storageProvider.getAccessToken(),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> categoriesLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? List<RadioCategoryItem>)?.let {
                                categoriesListListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun onGetAccountInfo() {
        if (accountInfoListListener.value.isNotEmpty()) return
        accountInfoUseCase.execute(
            storageProvider.getAccessToken(),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> accountLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? List<RadioAccountItem>)?.let {
                                accountInfoListListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun onGetHomeScreenFeedInfo(context: Context) {
        if (feedContentListener.value.isNotEmpty()) return
        getHomeScreenItems.execute(
            GetHomeScreenItemsUseCase.RequestValue(storageProvider.getAccessToken(), android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R, NotificationManagerCompat.from(context).areNotificationsEnabled()),
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
        return arrayListOf(getHomeScreenItems, categoriesUseCase, accountInfoUseCase)
    }

}
