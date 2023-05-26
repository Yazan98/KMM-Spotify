package com.yazantarifi.radio

object RadioApplicationMessages {

    private val APPLICATION_MESSAGES = HashMap<String, String>().apply {
        put("login_message_success:en", "Welcome to Reddit Client Application :D")
    }

    fun getMessage(key: String): String {
        return APPLICATION_MESSAGES.get(key + ":" + RadioApplicationConfigurations.applicationLanguage) ?: ""
    }

}
