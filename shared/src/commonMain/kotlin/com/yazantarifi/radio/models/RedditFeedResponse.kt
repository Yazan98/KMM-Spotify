package com.yazantarifi.radio.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.round

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
    @SerialName("author") val authorFullName: String? = "",
    @SerialName("link_flair_text") val linkText: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("post_hint") val postType: String? = "",
    @SerialName("thumbnail") val image: String? = "",
    @SerialName("ups") val upVotes: Long? = 0L,
    @SerialName("downs") val downVotes: Long? = 0L,
    @SerialName("score") val score: Long? = 0L,
    @SerialName("subreddit_subscribers") val subRedditSubscribers: Long? = 0L,
    @SerialName("upvote_ratio") val upVotesRatio: Double? = 0.0,
    @SerialName("title") val title: String? = "",
    @SerialName("subreddit_name_prefixed") val subRedditPrefixed: String? = "",
    @SerialName("saved") val isSaved: Boolean? = false,
    @SerialName("author_premium") val isPremium: Boolean? = false,
    @SerialName("over_18") val isOver18: Boolean? = false,
    @SerialName("pwls") val pwls: Int? = 0,
    @SerialName("num_comments") val comments: Int? = 0,
    @SerialName("thumbnail_height") val imageHeight: Int? = 0,
    @SerialName("thumbnail_width") val imageWidth: Int? = 0,
    @SerialName("is_video") val isVideo: Boolean? = false,
    @SerialName("all_awardings") val awardings: List<RedditPostAwardings>? = null,
    @SerialName("preview") val preview: RedditPostPreviewMedia? = null,
    @SerialName("media") val media: RedditPostVideoContainer? = null,
    @SerialName("secure_media") val securedMedia: RedditPostVideoContainer? = null
) {
    fun getUps(): String {
        return addCommasToNumber((upVotes ?: 0).toDouble()).replace(".0", "")

    }

    fun getDowns(): String {
        return addCommasToNumber((downVotes ?: 0).toDouble()).replace(".0", "")

    }

    fun getComments(): String {
        return addCommasToNumber((comments ?: 0).toDouble()).replace(".0", "")
    }

    private fun addCommasToNumber(number: Double): String {
        val numberString = number.toString()
        val parts = numberString.split(".")
        val integerPart = parts[0]
        val decimalPart = if (parts.size > 1) ".${parts[1]}" else ""

        val integerWithCommas = addCommasToIntegerPart(integerPart)
        return "$integerWithCommas$decimalPart"
    }

    private fun addCommasToIntegerPart(integerPart: String): String {
        val reversed = integerPart.reversed()
        val stringBuilder = StringBuilder()

        for (i in reversed.indices) {
            stringBuilder.append(reversed[i])
            if ((i + 1) % 3 == 0 && i != reversed.length - 1) {
                stringBuilder.append(",")
            }
        }

        return stringBuilder.reverse().toString()
    }
}

@Serializable
data class RedditPostVideoContainer(
    @SerialName("reddit_video") val videoContent: RedditPostVideoContent? = null
)

@Serializable
data class RedditPostVideoContent(
    @SerialName("bitrate_kbps") val bitrate: Long? = 0L,
    @SerialName("fallback_url") val url: String? = "",
    @SerialName("duration") val duration: Long? = 0L
)

@Serializable
data class RedditPostPreviewMedia(
    @SerialName("images") val images: List<RedditPostImage>? = null
)

@Serializable
data class RedditPostImage(
    @SerialName("source") val source: RedditPostImageSource? = null
)

@Serializable
data class RedditPostImageSource(
    @SerialName("url") val url: String? = "",
    @SerialName("width") val width: Int? = 0,
    @SerialName("height") val height: Int? = 0,
) {
    fun getImageUrl(): String {
        return url?.replace("amp;", "") ?: ""
    }
}

@Serializable
data class RedditPostAwardings(
    @SerialName("icon_url") val url: String? = "",
    @SerialName("author") val author: String? = "",
    @SerialName("id") val id: String? = "",
    @SerialName("created_utc") val createdSort: Double? = 0.0,
    @SerialName("subreddit_id") val subRedditId: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("link_flair_background_color") val backgroundColor: String? = "",
)