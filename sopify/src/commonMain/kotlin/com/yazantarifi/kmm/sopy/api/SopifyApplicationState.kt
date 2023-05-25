package com.yazantarifi.kmm.sopy.api

sealed class SopifyApplicationState<out T> {
    data class Success<out T : Any>(
        val data: T?
    ) : SopifyApplicationState<T>()

    object Loading: SopifyApplicationState<Nothing>()
    object Loaded: SopifyApplicationState<Nothing>()

    data class Error(
        val exception: Throwable? = null,
        val responseCode: Int = -1
    ) : SopifyApplicationState<Nothing>()

    fun handleResult(onSuccess: ((responseData: T?) -> Unit)?, onError: ((error: Error) -> Unit)?, onLoading: ((loadingState: Boolean) -> Unit)? = null) {
        when (this) {
            is Success -> {
                onSuccess?.invoke(this.data)
            }
            is Error -> {
                onError?.invoke(this)
            }
            is Loading -> {
                onLoading?.invoke(true)
            }
            is Loaded -> {
                onLoading?.invoke(false)
            }
        }
    }
}