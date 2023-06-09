package com.yazantarifi.radio.composable.composables.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.base.useCases.SopifyScope
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.account.AccountHeaderItem
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Job

@Composable
fun AccountInfoComposable(item: AccountHeaderItem) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        val resource = lazyPainterResource(item.image ?: "") {
            coroutineContext = Job() + SopifyScope().getCoroutineDispatcher()
        }

        if (resource is Resource.Loading) {
            Box(
                Modifier.size(90.dp).clip(CircleShape).background(
                    getSecondTextColor()
                )) {
                Text("Loading ...", modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        } else {
            KamelImage(
                resource = resource,
                contentDescription = item.name ?: "",
                modifier = Modifier.size(90.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(10.dp))
        Text(item.name, color = getTextColor(), textAlign = TextAlign.Center, maxLines = 1, fontSize = TextUnit(15f, TextUnitType.Sp))
        Text(item.email, color = getSecondTextColor(), textAlign = TextAlign.Center, maxLines = 1, fontSize = TextUnit(15f, TextUnitType.Sp))
        Spacer(Modifier.height(10.dp))
    }
}