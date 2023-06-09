package com.yazantarifi.kmm.radio.base.useCases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class SopifyScope {
    actual fun getCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}