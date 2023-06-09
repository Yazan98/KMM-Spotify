package com.yazantarifi.radio.composable.models.items

import com.yazantarifi.radio.composable.models.RadioHomeItem


class RadioCategoryItem(
    val id: String,
    val name: String,
    val icon: String,
    val loadingMessage: String
): RadioHomeItem {
    override fun getItemViewType(): Int {
        return RadioHomeItem.TYPE_CATEGORY
    }

    override fun isMultiItems(): Boolean {
        return false
    }

    override fun getContentList(): List<RadioHomeItem> {
        return arrayListOf()
    }
}
