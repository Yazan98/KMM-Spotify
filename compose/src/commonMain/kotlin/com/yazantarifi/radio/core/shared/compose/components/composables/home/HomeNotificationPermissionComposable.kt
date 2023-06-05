package com.yazantarifi.radio.core.shared.compose.components.composables.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yazantarifi.radio.core.shared.compose.components.composables.getSecondTextColor
import com.yazantarifi.radio.core.shared.compose.components.models.HomeNotificationPermissionItem

@Composable
fun HomeNotificationPermissionComposable(item: HomeNotificationPermissionItem) {
    if (!item.isNotificationsPermissionEnabled) {
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)).background(getSecondTextColor())) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row {
                        Icon(Icons.Default.Notifications, contentDescription = "Notification Icon", modifier = Modifier.size(50.dp), tint = Color.White)
                        Column {
                            Text(item.title, color = Color.White, fontSize = TextUnit(15f, TextUnitType.Sp))
                            Spacer(Modifier.height(5.dp))
                            Text(item.message, color = Color.White, fontSize = TextUnit(12f, TextUnitType.Sp))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(23.dp),
                            border = BorderStroke(3.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Color.White),
                            modifier = Modifier.weight(0.4f)
                        ) {
                            Text(
                                text = item.enableButtonText,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 2.dp),
                                color = Color.Black
                            )
                        }

                        Spacer(Modifier.width(20.dp))
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(23.dp),
                            border = BorderStroke(3.dp, Color.White),
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Color.White),
                            modifier = Modifier.weight(0.4f)
                        ) {
                            Text(
                                text = item.disableButtonText,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 2.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}