package com.yazantarifi.radio.core.shared.compose.components

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class ComposeScope {
    actual fun getScope(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}