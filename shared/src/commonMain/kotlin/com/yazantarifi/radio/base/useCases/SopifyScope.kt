package com.yazantarifi.radio.base.useCases

import kotlinx.coroutines.CoroutineDispatcher

expect class SopifyScope() {
    fun getCoroutineDispatcher(): CoroutineDispatcher
}
