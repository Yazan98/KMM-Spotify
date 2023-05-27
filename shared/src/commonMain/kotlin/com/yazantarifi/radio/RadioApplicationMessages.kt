package com.yazantarifi.radio

object RadioApplicationMessages {

    private val APPLICATION_MESSAGES = HashMap<String, String>().apply {
        put("login_message_success:en", "Welcome to Reddit Client Application :D")
        put("loading_feed:en", "Loading Feed ...")
        put("in:en", "in")
        put("hot:en", "Hot")
        put("new:en", "New")
        put("top:en", "Top")
        put("loading_discover:0:en", "Loading Hot News")
        put("loading_discover:1:en", "Loading New News")
        put("loading_discover:2:en", "Loading Top News")
    }

    fun getMessage(key: String): String {
        return APPLICATION_MESSAGES.get(key + ":" + RadioApplicationConfigurations.applicationLanguage) ?: ""
    }

}
