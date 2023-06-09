package com.yazantarifi.radio.mappers

import com.yazantarifi.radio.composable.models.HomeAlbumsItem
import com.yazantarifi.radio.composable.models.HomeCategoriesItem
import com.yazantarifi.radio.composable.models.HomePlaylistsItem
import com.yazantarifi.radio.composable.models.HomeSectionHeaderItem
import com.yazantarifi.radio.composable.models.RadioHomeItem


class HomeScreenVerticalGridMapper {
    fun getVerticalItems(items: List<RadioHomeItem?>): List<RadioHomeItem> {
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
