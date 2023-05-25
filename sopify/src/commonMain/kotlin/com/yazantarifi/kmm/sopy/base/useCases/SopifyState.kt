package com.yazantarifi.kmm.sopy.base.useCases

sealed interface SopifyState {

    data class SopifySuccessState(val payload: Any): SopifyState

    data class SopifyErrorState(val exception: Throwable): SopifyState

    data class SopifyLoadingState(val isLoading: Boolean): SopifyState

    object SopifyEmptyState: SopifyState

}