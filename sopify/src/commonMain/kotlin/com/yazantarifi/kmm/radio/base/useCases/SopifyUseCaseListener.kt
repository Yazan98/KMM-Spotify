package com.yazantarifi.kmm.radio.base.useCases

interface SopifyUseCaseListener {

    fun onStateUpdated(newState: SopifyState)

}