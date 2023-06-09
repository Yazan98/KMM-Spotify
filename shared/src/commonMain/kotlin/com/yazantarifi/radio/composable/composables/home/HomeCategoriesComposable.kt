package com.yazantarifi.radio.composable.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.base.useCases.SopifyScope
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.HomeCategoriesItem
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Job

@Composable
fun HomeCategoriesComposable(itemParent: HomeCategoriesItem, onCategoryClickListener: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Text(itemParent.title, color = getTextColor(), fontSize = TextUnit(15f, TextUnitType.Sp))
        Spacer(Modifier.height(10.dp))
        itemParent.items?.let {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(it) { item ->
                    Column(modifier = Modifier.padding(end = 10.dp, bottom = 10.dp).clickable { onCategoryClickListener(item.id) }) {
                        val resource = lazyPainterResource(item.icon ?: "") {
                            coroutineContext = Job() + SopifyScope().getCoroutineDispatcher()
                        }

                        if (resource is Resource.Loading) {
                            Box(
                                Modifier.size(160.dp).clip(RoundedCornerShape(8.dp)).background(
                                    getSecondTextColor()
                                )) {
                                Text(itemParent.loadingMessage, modifier = Modifier.align(Alignment.Center), color = Color.White)
                            }
                        } else {
                            KamelImage(
                                resource = resource,
                                contentDescription = item.name ?: "",
                                modifier = Modifier.size(160.dp).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(Modifier.height(10.dp))
                        Text(item.name ?: "", modifier = Modifier.width(130.dp), color = getTextColor(), maxLines = 1)
                    }
                }
            }
        }
    }
}