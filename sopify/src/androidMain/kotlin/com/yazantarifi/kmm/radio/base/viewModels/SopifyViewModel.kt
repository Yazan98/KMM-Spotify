package com.yazantarifi.kmm.radio.base.viewModels

import com.yazantarifi.kmm.radio.base.useCases.SopifyEmptyState
import com.yazantarifi.kmm.radio.base.useCases.SopifyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class SopifyViewModel<Action>: SopifyBaseViewModel<Action, MutableStateFlow<SopifyState>>() {

    val errorScreenListener: MutableStateFlow<Throwable?> by lazy { MutableStateFlow(null) }
    val errorMessageListener: MutableStateFlow<String?> by lazy { MutableStateFlow("") }
    init {
        initViewModelState()
    }

    final override fun initViewModelState() {
        if (state == null) {
            this.state = MutableStateFlow(getInitialState())
            scope.launch(coroutineDispatch) {
                state?.collect {
                    onAcceptNewState(it)
                }
            }
        }
    }

    override fun getInitialState(): SopifyState {
        return SopifyEmptyState
    }

    private fun onAcceptNewState(newState: SopifyState) {
        getCurrentState()?.update { newState }
    }

}
