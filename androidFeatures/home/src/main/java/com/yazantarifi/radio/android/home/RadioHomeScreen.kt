package com.yazantarifi.radio.android.home

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
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
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.screens.SopifyStateScreen
import com.yazantarifi.radio.android.home.composables.AccountComposable
import com.yazantarifi.radio.android.home.composables.CategoriesComposable
import com.yazantarifi.radio.android.home.composables.FeedComposable
import com.yazantarifi.radio.android.home.viewModels.HomeAction
import com.yazantarifi.radio.android.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RadioHomeScreen: SopifyStateScreen<HomeAction, HomeViewModel>() {

    private val isNotificationPermissionEnabled = mutableStateOf(false)
    private var navController: NavHostController? = null
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onPermissionResult(result.resultCode == RESULT_OK)
    }

    private val requestNotificationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
        onPermissionResult(isGranted)
    }

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
        val isNotificationPermissionEnabledListener by remember {
            isNotificationPermissionEnabled
        }

        NavHost(
            navController = navController!!,
            startDestination = "home",
            modifier = Modifier.background(getBackgroundColor())
        ) {
            composable("home") { FeedComposable(viewModel, isNotificationPermissionEnabledListener) }
            composable("discover") { CategoriesComposable(viewModel) }
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
                                Text(text = RadioApplicationMessages.getMessage("home_tab_1"))
                            } else if (index == 1) {
                                Text(text = RadioApplicationMessages.getMessage("home_tab_2"))
                            } else {
                                Text(text = RadioApplicationMessages.getMessage("home_tab_3"))
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

    fun executePermission(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            requestPermissionLauncher.launch(intent)
        }
    }

    /**
     * This Should not be the Case in Realworld App, it's Only for Demo
     */
    override fun onResume() {
        super.onResume()
        val isNotificationsEnabled = NotificationManagerCompat.from(this).areNotificationsEnabled()
        if (isNotificationsEnabled) {
            isNotificationPermissionEnabled.value = true
        }
    }

    private fun onPermissionResult(isOk: Boolean) {
        if (isOk) {
            // Notification permission granted
            errorScreenMessageListener.value = RadioApplicationMessages.getMessage("notification_permission_granted")
        } else {
            // Notification permission denied
            errorScreenMessageListener.value = RadioApplicationMessages.getMessage("notification_permission_message")
        }
    }

}