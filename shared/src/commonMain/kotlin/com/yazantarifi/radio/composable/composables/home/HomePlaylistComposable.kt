package com.yazantarifi.radio.composable.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.base.useCases.SopifyScope
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.items.RadioPlaylist
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Job

@Composable
fun HomePlaylistComposable(item: RadioPlaylist) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        val resource = lazyPainterResource(item.image ?: "") {
            coroutineContext = Job() + SopifyScope().getCoroutineDispatcher()
        }

        if (resource is Resource.Loading) {
            Box(Modifier.aspectRatio(1f).fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(getSecondTextColor())) {
                Text(item.loadingMessage, modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        } else {
            KamelImage(
                resource = resource,
                contentDescription = item.name ?: "",
                modifier = Modifier.aspectRatio(1f).fillMaxWidth().clip(RoundedCornerShape(8.dp))
            )
        }

        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(item.name ?: "", modifier = Modifier.fillMaxWidth(), color = getTextColor(), maxLines = 1)
            Text(item.numberOfTracks.toString(), color = getTextColor(), maxLines = 1)
        }
        Text(item.ownerName ?: "", color = getSecondTextColor(), modifier = Modifier.fillMaxWidth(), maxLines = 1)
    }
}
