package com.yazantarifi.radio.core.shared.compose.components.composables.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yazantarifi.radio.core.shared.compose.components.composables.getTextColor
import com.yazantarifi.radio.core.shared.compose.components.models.account.AccountSectionNameItem

@Composable
fun AccountSectionComposable(item: AccountSectionNameItem) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Text(item.name, color = getTextColor(), fontSize = TextUnit(15f, TextUnitType.Sp))
        Spacer(Modifier.height(5.dp))
    }
}