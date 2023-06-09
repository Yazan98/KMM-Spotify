package com.yazantarifi.radio.composable.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.base.useCases.SopifyScope
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.items.RadioCategoryItem
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Job

@Composable
fun HomeCategoryComposable(item: RadioCategoryItem, onCategoryClickListener: (String) -> Unit) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp).clickable { onCategoryClickListener(item.id) }) {
        val resource = lazyPainterResource(item.icon ?: "") {
            coroutineContext = Job() + SopifyScope().getCoroutineDispatcher()
        }

        if (resource is Resource.Loading) {
            Box(
                Modifier.aspectRatio(1f).fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(
                    getSecondTextColor()
                )) {
                Text(item.loadingMessage, modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        } else {
            KamelImage(
                resource = resource,
                contentDescription = item.name ?: "",
                modifier = Modifier.aspectRatio(1f).fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(10.dp))
        Text(item.name ?: "", modifier = Modifier.fillMaxWidth(), color = getTextColor(), maxLines = 1, textAlign = TextAlign.Center)
    }
}