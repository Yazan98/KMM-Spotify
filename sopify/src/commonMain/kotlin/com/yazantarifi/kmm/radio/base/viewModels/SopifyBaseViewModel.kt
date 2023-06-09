package com.yazantarifi.kmm.radio.base.viewModels


import com.yazantarifi.kmm.radio.base.useCases.SopifyState
import com.yazantarifi.kmm.radio.base.useCases.SopifyUseCaseType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

expect abstract class SopifyBaseViewModel<Action, StateType> {

    var state: StateType?
    val coroutineDispatch: CoroutineDispatcher
    val scope: CoroutineScope

    fun execute(action: Action)

    abstract fun initViewModelState()

    abstract fun getInitialState(): SopifyState

    abstract suspend fun onNewActionTriggered(action: Action)

    fun getSupportedUseCases(): ArrayList<SopifyUseCaseType>

    fun clear()

    protected fun getCurrentState(): StateType?

}
