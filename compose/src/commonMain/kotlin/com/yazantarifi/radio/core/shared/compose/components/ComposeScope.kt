package com.yazantarifi.radio.core.shared.compose.components

import kotlinx.coroutines.CoroutineDispatcher

expect class ComposeScope constructor() {
    fun getScope(): CoroutineDispatcher
}