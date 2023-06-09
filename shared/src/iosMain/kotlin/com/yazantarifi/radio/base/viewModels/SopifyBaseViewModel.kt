package com.yazantarifi.radio.base.viewModels

import com.yazantarifi.radio.base.useCases.SopifyScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

import com.yazantarifi.radio.base.useCases.SopifyState
import com.yazantarifi.radio.base.useCases.SopifyUseCaseType

actual abstract class SopifyBaseViewModel<Action, StateType> {

    actual var state: StateType? = null
    actual val coroutineDispatch: CoroutineDispatcher = SopifyScope().getCoroutineDispatcher()
    actual val scope: CoroutineScope = object: CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Job() + coroutineDispatch
    }

    actual fun execute(action: Action) {
        scope.launch(coroutineDispatch) {
            onNewActionTriggered(action)
        }
    }

    actual abstract fun initViewModelState()
    actual abstract fun getInitialState(): SopifyState
    actual abstract suspend fun onNewActionTriggered(action: Action)
    actual fun getSupportedUseCases(): ArrayList<SopifyUseCaseType> {
        return arrayListOf()
    }

    actual fun clear() {
        getSupportedUseCases().forEach {
            it.clear()
        }
    }

    protected actual fun getCurrentState(): StateType? {
        return this.state
    }

}