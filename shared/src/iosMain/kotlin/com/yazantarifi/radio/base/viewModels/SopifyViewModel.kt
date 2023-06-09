package com.yazantarifi.radio.base.viewModels

import com.yazantarifi.radio.base.useCases.SopifyEmptyState
import com.yazantarifi.radio.base.useCases.SopifyState

abstract class SopifyViewModel<Action>: SopifyBaseViewModel<Action, Unit>() {

    private var errorListener: SopifyViewModelListeners? = null
    init {
        initViewModelState()
    }

    fun onAcceptExceptionState(exception: Throwable) {
        this.errorListener?.onErrorScreenEventTriggered(exception)
    }

    fun onAcceptErrorMessage(message: String) {
        this.errorListener?.onErrorMessageTriggered(message)
    }

    fun onAttachListenerInstance(errorListener: SopifyViewModelListeners) {
        this.errorListener = errorListener
    }

    final override fun initViewModelState() = Unit
    override fun getInitialState(): SopifyState {
        return SopifyEmptyState
    }

}
