package com.yazantarifi.radio

object RadioApplicationMessages {

    private val APPLICATION_MESSAGES = HashMap<String, String>().apply {
        put("login_message_success:en", "Welcome to Spotify Client Application :D")
        put("loading_feed:en", "Loading Home Screen ...")
        put("home_title:en", "Welcome to Spotify Clone Application")
        put("home_change_design:en", "Change Items Scroll")
        put("featured_playlists:en", "Featured Playlists")
        put("loading_image:en", "Loading ...")
        put("albums:en", "Albums - New Releases")
        put("categories:en", "Top Picked Categories")
        put("top_pop:en", "Top Picked Pop Playlists")
        put("top_mood:en", "Top Picked Mood Playlists")
        put("top_gaming:en", "Top Picked Gaming Playlists")
        put("top_workout:en", "Top Picked Workout Playlists")
        put("notification_permission_title:en", "Allow Radio to Show Notifications")
        put("notification_permission_enable:en", "Enable")
        put("notification_permission_disable:en", "Disable")
        put("spotify_app_open_title:en", "Spotify App")
        put("spotify_app_open_button:en", "Open App")
        put("home_tab_1:en", "Home")
        put("home_tab_2:en", "Categories")
        put("home_tab_3:en", "Account")
        put("account_section_info:en", "Account Info")
        put("account_section_info_more:en", "Account More Info")
        put("account_section_followers:en", "Followers")
        put("account_section_product:en", "Product")
        put("account_section_following_artists:en", "Following Artists")
        put("loading_categories:en", "Loading All Categories ...")
        put("loading_account:en", "Loading Account Information")
        put("account_section_played_tracks:en", "Last Played Tracks")
        put("spotify_app_open_message:en", "Open Spotify Application to Get Access on A lot of Music More than This Content")
        put("notification_permission_message:en", "Radio Application Need To Show Notifications, Click on Allow to Take Notification Permission :D")
        put("notification_permission_warning_message:en", "Warning: You Cant get Notifications Unless Notifications Enabled")
        put("home_des:en", "This Application is a Simple Example about Kotlin Multiplatform Mobile apps with Compose Multiplatform Powered by Spotify Api ans Sopy Developed by @Yazan98 - Github Open Source")
    }

    fun getMessage(key: String): String {
        return APPLICATION_MESSAGES.get(key + ":" + RadioApplicationConfigurations.applicationLanguage) ?: ""
    }

}
