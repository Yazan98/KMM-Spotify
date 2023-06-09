package com.yazantarifi.radio.android.home.viewModels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationManagerCompat
import com.yazantarifi.radio.base.context.SopifyStorageProvider
import com.yazantarifi.radio.base.useCases.SopifyEmptyState
import com.yazantarifi.radio.base.useCases.SopifyErrorState
import com.yazantarifi.radio.base.useCases.SopifyLoadingState
import com.yazantarifi.radio.base.useCases.SopifyState
import com.yazantarifi.radio.base.useCases.SopifySuccessState
import com.yazantarifi.radio.base.useCases.SopifyUseCaseListener
import com.yazantarifi.radio.base.useCases.SopifyUseCaseType
import com.yazantarifi.radio.base.viewModels.SopifyViewModel
import com.yazantarifi.radio.composable.models.HomeLayoutDesignItem
import com.yazantarifi.radio.composable.models.HomeNotificationPermissionItem
import com.yazantarifi.radio.composable.models.RadioHomeItem
import com.yazantarifi.radio.composable.models.account.RadioAccountItem
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
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
    var feedContentListener = mutableStateListOf<RadioHomeItem>()

    val categoriesListListener: MutableState<ArrayList<RadioCategoryItem?>> by lazy { mutableStateOf(arrayListOf()) }
    val categoriesLoadingListener by lazy { mutableStateOf(false) }

    val accountLoadingListener by lazy { mutableStateOf(false) }
    val accountInfoListListener: MutableState<ArrayList<RadioAccountItem>> by lazy { mutableStateOf(arrayListOf()) }

    override suspend fun onNewActionTriggered(action: HomeAction) {
        when (action) {
            is HomeAction.GetFeed -> onGetHomeScreenFeedInfo(action.context)
            is HomeAction.GetCategoriesAction -> onGetCategories()
            is HomeAction.GetAccountInfoAction -> onGetAccountInfo()
            is HomeAction.RemoveNotificationPermissionAction -> onRemoveNotificationPermissionItem()
        }
    }

    private fun onRemoveNotificationPermissionItem() {
        var itemPosition = -1
        feedContentListener.let {
            for ((index, value) in it.withIndex()) {
                if (value is HomeNotificationPermissionItem) {
                    itemPosition = index
                    break
                }
            }
        }

        if (itemPosition == -1) return
        feedContentListener.removeAt(itemPosition)
    }

    private fun onGetCategories() {
        if (categoriesListListener.value.isNotEmpty()) return
        categoriesUseCase.execute(
            storageProvider.getAccessToken(),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyEmptyState -> {}
                            is SopifyLoadingState -> categoriesLoadingListener.value = newState.isLoading
                            is SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifySuccessState -> (newState.payload as? List<RadioCategoryItem>)?.let {
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
                            is SopifyEmptyState -> {}
                            is SopifyLoadingState -> accountLoadingListener.value = newState.isLoading
                            is SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifySuccessState -> (newState.payload as? List<RadioAccountItem>)?.let {
                                accountInfoListListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun onGetHomeScreenFeedInfo(context: Context) {
        if (feedContentListener.isNotEmpty()) return
        getHomeScreenItems.execute(
            GetHomeScreenItemsUseCase.RequestValue(storageProvider.getAccessToken(), android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R, NotificationManagerCompat.from(context).areNotificationsEnabled()),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyEmptyState -> {}
                            is SopifyLoadingState -> feedLoadingListener.value = newState.isLoading
                            is SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifySuccessState -> (newState.payload as? List<RadioHomeItem>)?.let {
                                feedContentListener.addAll(it)
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
