package com.yazantarifi.kmm.sopy.base.useCases

import kotlinx.coroutines.CoroutineDispatcher

expect class SopifyScope() {
    fun getCoroutineDispatcher(): CoroutineDispatcher
}
