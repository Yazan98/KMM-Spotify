package com.yazantarifi.radio.api

object RedditApiInfo {

    const val API_BASE_URL = "https://www.reddit.com/api/v1/"

}

enum class ApiUrls constructor(val requestUrl: String) {
    ACCESS_TOKEN(RedditApiInfo.API_BASE_URL + "access_token")
}