package com.yazantarifi.radio.android.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.android.home.composables.AccountComposable
import com.yazantarifi.radio.android.home.composables.DiscoverComposable
import com.yazantarifi.radio.android.home.composables.FeedComposable
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioHomeScreen: SopifyStateScreen<HomeAction, HomeViewModel>() {

    private var navController: NavHostController? = null
    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, RadioHomeScreen::class.java))
            context.finish()
        }
    }

    @Composable
    override fun onScreenContent(savedInstanceState: Bundle?): HomeViewModel {
        navController = rememberNavController()
        val viewModel: HomeViewModel = hiltViewModel()
        NavHost(
            navController = navController!!,
            startDestination = "home",
            modifier = Modifier.background(getBackgroundColor())
        ) {
            composable("home") { FeedComposable(viewModel) }
            composable("discover") { DiscoverComposable(viewModel) }
            composable("account") { AccountComposable(viewModel) }
        }
        return viewModel
    }

    @Composable
    override fun SopifyBottomBarComposable() {
        var selectedItem by remember { mutableStateOf(0) }
        NavigationBar(containerColor = getSeconderyCardsColor()) {
            arrayListOf(0, 1, 2)
                .forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            if (index == 0) {
                                Icon(Icons.Filled.Home, contentDescription = null, tint = getTextColor())
                            } else if (index == 1) {
                                Icon(Icons.Filled.List, contentDescription = null, tint = getTextColor())
                            } else {
                                Icon(Icons.Filled.AccountCircle, contentDescription = null, tint = getTextColor())
                            }
                        },
                        label = {
                            if (index == 0) {
                                Text(text = "Home")
                            } else if (index == 1) {
                                Text(text = "Discover")
                            } else {
                                Text(text = "Account")
                            }
                        },
                        selected = selectedItem == index,
                        onClick = {
                            if (selectedItem != index) {
                                selectedItem = index
                                if (selectedItem == 0) {
                                    navController?.navigate("home")
                                } else if (selectedItem == 1) {
                                    navController?.navigate("discover")
                                } else {
                                    navController?.navigate("account")
                                }
                            }
                        }
                    )
                }
        }
    }

    override fun isDefaultToolbarEnabled(): Boolean {
        return true
    }

    override fun isBottomBarEnabled(): Boolean {
        return true
    }

}