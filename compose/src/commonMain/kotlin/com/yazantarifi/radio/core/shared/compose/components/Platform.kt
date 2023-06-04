package com.yazantarifi.radio.core.shared.compose.components

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform