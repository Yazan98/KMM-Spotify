package com.yazantarifi.radio.base.useCases

interface SopifyUseCaseListener {

    fun onStateUpdated(newState: SopifyState)

}