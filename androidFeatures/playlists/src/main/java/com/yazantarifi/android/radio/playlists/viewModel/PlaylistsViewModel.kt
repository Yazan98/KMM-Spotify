package com.yazantarifi.android.radio.playlists.viewModel

import androidx.compose.runtime.mutableStateOf
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.kmm.sopy.base.useCases.SopifyState
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseListener
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseType
import com.yazantarifi.kmm.sopy.base.viewModels.SopifyViewModel
import com.yazantarifi.radio.core.shared.compose.components.models.items.RadioPlaylist
import com.yazantarifi.radio.useCases.GetCategoryPlaylistsUseCase
import com.yazantarifi.radio.useCases.GetCategoryPlaylistsUseCaseAlias
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val storageProvider: SopifyStorageProvider,
    private val getPlaylistsUseCase: GetCategoryPlaylistsUseCaseAlias
): SopifyViewModel<PlaylistAction>() {

    val screenContentListener by lazy { mutableStateOf<ArrayList<RadioPlaylist>>(arrayListOf()) }
    val loadingListener by lazy { mutableStateOf(false) }
    val paginationLoadingListener by lazy { mutableStateOf(false) }

    override suspend fun onNewActionTriggered(action: PlaylistAction) {
        when (action) {
            is PlaylistAction.GetPlaylistsByCategoryIdAction -> getPlaylistsByCategoryId(action.id)
            is PlaylistAction.GetPaginationAction -> getPaginationPlaylists()
        }
    }

    private fun getPaginationPlaylists() {
        if (paginationLoadingListener.value) return
        getPlaylistsUseCase.getInstance()?.execute(
            GetCategoryPlaylistsUseCase.RequestParams("", storageProvider.getAccessToken()),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> paginationLoadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? List<RadioPlaylist>)?.let {
                                screenContentListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun getPlaylistsByCategoryId(id: String) {
        getPlaylistsUseCase.getInstance()?.execute(
            GetCategoryPlaylistsUseCase.RequestParams(id, storageProvider.getAccessToken()),
            object : SopifyUseCaseListener {
                override fun onStateUpdated(newState: SopifyState) {
                    scope.launch(Dispatchers.Main) {
                        when (newState) {
                            is SopifyState.SopifyEmptyState -> {}
                            is SopifyState.SopifyLoadingState -> loadingListener.value = newState.isLoading
                            is SopifyState.SopifyErrorState -> errorMessageListener.value = newState.exception.message ?: ""
                            is SopifyState.SopifySuccessState -> (newState.payload as? List<RadioPlaylist>)?.let {
                                screenContentListener.value.addAll(it)
                            }
                        }
                    }
                }
            }
        )
    }

    override fun getSupportedUseCases(): ArrayList<SopifyUseCaseType> {
        val useCaseInstance = getPlaylistsUseCase.getInstance() ?: return arrayListOf()
        return arrayListOf(useCaseInstance)
    }

}
