package com.yazantarifi.radio.base.viewModels

interface SopifyViewModelListeners {

    fun onErrorScreenEventTriggered(exception: Throwable)

    fun onErrorMessageTriggered(message: String)

}
