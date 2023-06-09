package com.yazantarifi.radio.composable.composables.account

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.composable.composables.getSecondTextColor
import com.yazantarifi.radio.composable.composables.getTextColor
import com.yazantarifi.radio.composable.models.account.AccountListSectionItem

@Composable
fun AccountSectionValueComposable(item: AccountListSectionItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Spacer(Modifier.width(5.dp))
        Text(item.title, color = getTextColor(), fontSize = TextUnit(13f, TextUnitType.Sp))
        Spacer(Modifier.width(150.dp))
        Text(item.value, color = getSecondTextColor(), fontSize = TextUnit(13f, TextUnitType.Sp))
    }
}
