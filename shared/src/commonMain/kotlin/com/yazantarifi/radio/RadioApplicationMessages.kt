package com.yazantarifi.radio

object RadioApplicationMessages {

    private val APPLICATION_MESSAGES = HashMap<String, String>().apply {
        put("login_message_success:en", "Welcome to Reddit Client Application :D")
        put("loading_feed:en", "Loading Home Screen ...")
        put("home_title:en", "Welcome to Spotify Clone Application")
        put("home_change_design:en", "Change Items Scroll")
        put("featured_playlists:en", "Featured Playlists")
        put("loading_image:en", "Image Loading ...")
        put("home_des:en", "This Application is a Simple Example about Kotlin Multiplatform Mobile apps with Compose Multiplatform Powered by Spotify Api ans Sopy Developed by @Yazan98 - Github Open Source")
    }

    fun getMessage(key: String): String {
        return APPLICATION_MESSAGES.get(key + ":" + RadioApplicationConfigurations.applicationLanguage) ?: ""
    }

}
