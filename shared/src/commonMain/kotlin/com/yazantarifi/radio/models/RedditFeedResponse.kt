package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditFeedResponse(
    @SerialName("data") val payload: RedditFeedData? = null
)

@Serializable
data class RedditFeedData(
    @SerialName("after") val after: String? = "",
    @SerialName("dist") val dist: Int? = 0,
    @SerialName("children") val children: List<RedditFeedPostPayload>? = null
)

@Serializable
data class RedditFeedPostPayload(
    @SerialName("kind") val kind: String? = "",
    @SerialName("data") val post: RedditFeedPost? = null
)

@Serializable
data class RedditFeedPost(
    @SerialName("subreddit") val subReddit: String? = "",
    @SerialName("selftext") val selftext: String? = "",
    @SerialName("author_fullname") val authorFullName: String? = "",
    @SerialName("link_flair_text") val linkText: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("post_hint") val postType: String? = "",
    @SerialName("thumbnail") val image: String? = "",
    @SerialName("ups") val upVotes: Long? = 0L,
    @SerialName("score") val score: Long? = 0L,
    @SerialName("subreddit_subscribers") val subRedditSubscribers: Long? = 0L,
    @SerialName("upvote_ratio") val upVotesRatio: Double? = 0.0,
    @SerialName("title") val title: String? = "",
    @SerialName("subreddit_name_prefixed") val subRedditPrefixed: String? = "",
    @SerialName("saved") val isSaved: Boolean? = false,
    @SerialName("author_premium") val isPremium: Boolean? = false,
    @SerialName("over_18") val isOver18: Boolean? = false,
    @SerialName("pwls") val pwls: Int? = 0,
    @SerialName("thumbnail_height") val imageHeight: Int? = 0,
    @SerialName("thumbnail_width") val imageWidth: Int? = 0,
    @SerialName("all_awardings") val awardings: List<RedditPostAwardings>? = null
)

@Serializable
data class RedditPostAwardings(
    @SerialName("is_video") val isVideo: Boolean? = false,
    @SerialName("url") val url: String? = "",
    @SerialName("author") val author: String? = "",
    @SerialName("id") val id: String? = "",
    @SerialName("created_utc") val createdSort: Double? = 0.0,
    @SerialName("subreddit_id") val subRedditId: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("link_flair_background_color") val backgroundColor: String? = "",
)