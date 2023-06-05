package com.yazantarifi.radio.mappers

import com.yazantarifi.radio.core.shared.compose.components.models.HomeAlbumsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeCategoriesItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomePlaylistsItem
import com.yazantarifi.radio.core.shared.compose.components.models.HomeSectionHeaderItem
import com.yazantarifi.radio.core.shared.compose.components.models.RadioHomeItem

class HomeScreenVerticalGridMapper {
    fun getVerticalItems(items: ArrayList<RadioHomeItem?>): List<RadioHomeItem> {
        val filteredItems = ArrayList<RadioHomeItem>()
        items.forEach {
            it?.let {
                if (it.isMultiItems()) {
                    if (it is HomeAlbumsItem) {
                        filteredItems.add(HomeSectionHeaderItem(it.title))
                    } else if (it is HomePlaylistsItem) {
                        filteredItems.add(HomeSectionHeaderItem(it.title))
                    } else if (it is HomeCategoriesItem) {
                        filteredItems.add(HomeSectionHeaderItem(it.title))
                    }

                    filteredItems.addAll(it.getContentList())
                } else {
                    filteredItems.add(it)
                }
            }
        }

        return filteredItems
    }
}
