package com.yazantarifi.radio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform