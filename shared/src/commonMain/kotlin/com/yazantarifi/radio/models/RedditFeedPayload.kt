package com.yazantarifi.radio.models

class RedditFeedPayload(
    val isHardReload: Boolean = false,
    val posts: List<RedditFeedPost?>? = null
)