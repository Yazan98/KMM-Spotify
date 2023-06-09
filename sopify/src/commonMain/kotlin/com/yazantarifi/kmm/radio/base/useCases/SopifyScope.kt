package com.yazantarifi.kmm.radio.base.useCases

import kotlinx.coroutines.CoroutineDispatcher

expect class SopifyScope() {
    fun getCoroutineDispatcher(): CoroutineDispatcher
}
