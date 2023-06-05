package com.yazantarifi.radio.android.home.viewModels

import android.content.Context


interface HomeAction {
    data class GetFeed(val context: Context): HomeAction
    object GetCategoriesAction: HomeAction
    object GetAccountInfo: HomeAction
}
