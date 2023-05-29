package com.yazantarifi.radio.android.core.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.yazantarifi.radio.android.core.R
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.models.RedditFeedPost

@Composable
fun PostListingComposable(post: RedditFeedPost) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            SeconderyColoredText(message = post.authorFullName ?: "")
            Spacer(modifier = Modifier.width(5.dp))
            ColoredText(message = " ${RadioApplicationMessages.getMessage("in")} ")
            Spacer(modifier = Modifier.width(5.dp))
            PrimaryColoredText(message = "r/${post.subReddit ?: ""}")
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
            if (height > 450) {
                height = 450
            }

            if (post.isVideo == false) {
                AsyncImage(
                    model = it.getImageUrl(),
                    contentDescription = post.title ?: "",
                    modifier = Modifier
                        .height(height.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(3.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box {
                    AsyncImage(
                        model = it.getImageUrl(),
                        contentDescription = post.title ?: "",
                        modifier = Modifier
                            .height(height.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(3.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height.dp)
                    ) {
                        AsyncImage(
                            model = R.drawable.play,
                            contentDescription = post.title ?: "",
                            modifier = Modifier
                                .height(55.dp)
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = R.drawable.up,
                    contentDescription = post.title ?: "",
                    modifier = Modifier
                        .height(20.dp)
                )
                Text(text = RadioApplicationMessages.getMessage("ups") + " : " + post.getUps(), maxLines = 1)
            }

            Spacer(modifier = Modifier.width(10.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = R.drawable.down,
                    contentDescription = post.title ?: "",
                    modifier = Modifier
                        .height(20.dp)
                )
                Text(text = RadioApplicationMessages.getMessage("downs") + " : " + post.getDowns(), maxLines = 1)
            }

            Spacer(modifier = Modifier.width(10.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = R.drawable.chat,
                    contentDescription = post.title ?: "",
                    modifier = Modifier
                        .height(20.dp)
                )
                Text(text = RadioApplicationMessages.getMessage("comments") + " : " + post.getComments(), maxLines = 1)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = SopifyStateScreen.getGreyColor())
    }
}