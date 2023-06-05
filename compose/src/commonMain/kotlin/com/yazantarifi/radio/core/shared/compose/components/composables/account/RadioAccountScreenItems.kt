package com.yazantarifi.radio.core.shared.compose.components.composables.account

data class AccountHeaderItem(
    val image: String,
    val name: String,
    val email: String,
    val type: String
): RadioAccountItem {
    override fun getViewType(): Int {
        return RadioAccountItem.TYPE_ACCOUNT_INFO
    }
}

data class AccountListSectionItem(
    val title: String,
    val value: String
): RadioAccountItem {
    override fun getViewType(): Int {
        return RadioAccountItem.TYPE_LIST_ITEM
    }
}

data class AccountSectionNameItem(
    val name: String
): RadioAccountItem {
    override fun getViewType(): Int {
        return RadioAccountItem.TYPE_SECTION
    }
}

data class AccountArtistItem(
    val name: String,
    val description: String,
    val followers: String,
    val image: String,
    val id: String
): RadioAccountItem {
    override fun getViewType(): Int {
        return RadioAccountItem.TYPE_ARTIST
    }
}
