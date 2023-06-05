package com.yazantarifi.radio.core.shared.compose.components.composables.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.core.shared.compose.components.ComposeScope
import com.yazantarifi.radio.core.shared.compose.components.composables.getSecondTextColor
import com.yazantarifi.radio.core.shared.compose.components.composables.getTextColor
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountTrackItem
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Job

@Composable
fun AccountTrackComposable(item: AccountTrackItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        val resource = lazyPainterResource(item.image ?: "") {
            coroutineContext = Job() + ComposeScope().getScope()
        }

        if (resource is Resource.Loading) {
            Box(
                Modifier.size(70.dp).clip(RoundedCornerShape(8.dp)).background(
                    getSecondTextColor()
                )) {
                Text("Loading", modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        } else {
            KamelImage(
                resource = resource,
                contentDescription = item.name ?: "",
                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(item.name ?: "", modifier = Modifier.fillMaxWidth(), color = getTextColor(), maxLines = 1)
            Text(item.description ?: "", modifier = Modifier.fillMaxWidth(), color = getSecondTextColor(), maxLines = 1)
        }
    }
}