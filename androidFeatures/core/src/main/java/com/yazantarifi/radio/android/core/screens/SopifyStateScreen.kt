package com.yazantarifi.radio.android.core.screens


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yazantarifi.radio.android.core.RadioTheme
import com.yazantarifi.radio.android.core.composables.RadioToolbar
import com.yazantarifi.radio.base.viewModels.SopifyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class SopifyStateScreen<Action, ViewModel: SopifyViewModel<Action>>: ComponentActivity() {

    protected val errorScreenListener: MutableState<Throwable?> by lazy { mutableStateOf(null) }
    protected val errorScreenMessageListener: MutableState<String?> by lazy { mutableStateOf(null) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RadioTheme {
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            if (isDefaultToolbarEnabled()) {
                                ApplicationDefaultToolbar()
                            } else {
                                getCustomToolbarComposable()
                            }
                        },
                        bottomBar = {
                            if (isBottomBarEnabled()) {
                                SopifyBottomBarComposable()
                            }
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            onScreenContent(savedInstanceState).apply {
                                setupErrorListeners(this)

                                val isErrorMessageExists = !TextUtils.isEmpty(errorScreenMessageListener.value)
                                if (isErrorMessageExists) {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(errorScreenMessageListener.value ?: "")
                                        errorMessageListener.value = ""
                                    }
                                }

                                val isErrorScreenExist = errorScreenListener.value != null
                                if (isErrorScreenExist) {
                                    startActivity(Intent(this@SopifyStateScreen, SopifyErrorScreen::class.java))
                                    errorScreenListener.value = null
                                }
                            }
                        }
                    }
                }
            }
        }

        onScreenStarted(savedInstanceState)
    }

    private fun setupErrorListeners(viewModel: ViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorScreenListener.collectLatest {
                    errorScreenListener.value = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorMessageListener.collectLatest {
                    errorScreenMessageListener.value = it
                }
            }
        }
    }

    @Composable
    fun getSeconderyCardsColor(): Color {
        return MaterialTheme.colorScheme.background
    }

    @Composable
    protected fun getBackgroundColor(): Color {
        return MaterialTheme.colorScheme.background
    }

    @Composable
    protected fun getApplicationColor(): Color {
        return Color(0xFF36FFBF)
    }

    @Composable
    fun getTextColor(): Color {
        return MaterialTheme.colorScheme.onBackground
    }

    protected open fun isDefaultToolbarEnabled(): Boolean {
        return true
    }

    @Composable
    protected fun ApplicationDefaultToolbar() {
        RadioToolbar("Radio", true, this)
    }

    @Composable
    protected open fun getCustomToolbarComposable() {
        RadioToolbar("Radio", true, this)
    }

    protected open fun isBottomBarEnabled(): Boolean {
        return false
    }

    @Composable
    protected open fun SopifyBottomBarComposable() {

    }

    companion object {
        @Composable
        fun getGreyColor(): Color {
            return when (isSystemInDarkTheme()) {
                true -> Color(0xFF000000)
                false -> Color(0xFFE2E1E1)
            }
        }

        @Composable
        fun getGreyTextColor(): Color {
            return when (isSystemInDarkTheme()) {
                true -> Color(0xFF000000)
                false -> Color(0xFFC5C4C4)
            }
        }

        @Composable
        fun getPrimaryColor(): Color {
            return Color(0xFF36FFBF)
        }
    }

    @Composable
    abstract fun onScreenContent(savedInstanceState: Bundle?): ViewModel

    open fun onScreenStarted(savedInstanceState: Bundle?) = Unit
}