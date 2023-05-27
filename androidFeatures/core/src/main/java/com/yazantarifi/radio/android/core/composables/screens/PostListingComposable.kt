package com.yazantarifi.radio.android.core.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.models.RedditFeedPost

@Composable
fun PostListingComposable(post: RedditFeedPost) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            ColoredText(message = post.name ?: "")
            Spacer(modifier = Modifier.width(5.dp))
            ColoredText(message = " ${RadioApplicationMessages.getMessage("in")} ")
            Spacer(modifier = Modifier.width(5.dp))
            ColoredText(message = "r/${post.subReddit ?: ""}")
            if (post.awardings?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.width(5.dp))
                post.awardings?.forEach {
                    AsyncImage(
                        model = it.url,
                        contentDescription = post.title ?: "",
                        modifier = Modifier
                            .size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        TitleText(message = post.title ?: "")
        if (post.selftext?.isNotEmpty() == true) {
            Spacer(modifier = Modifier.height(10.dp))
            SeconderyTitleText(message = post.selftext ?: "")
        }
        Spacer(modifier = Modifier.height(15.dp))
        post.preview?.images?.get(0)?.source?.let {
            var height = (it.height ?: 0)
            if (height > 500) {
                height = 500
            }

            AsyncImage(
                model = it.getImageUrl(),
                contentDescription = post.title ?: "",
                modifier = Modifier
                    .height(height.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(3.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(color = SopifyStateScreen.getGreyColor())
    }
}