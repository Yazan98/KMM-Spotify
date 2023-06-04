package com.yazantarifi.radio.android.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.kmm.sopy.base.useCases.SopifyState
import com.yazantarifi.kmm.sopy.base.useCases.SopifyUseCaseListener
import com.yazantarifi.radio.RadioApplicationMessages
import com.yazantarifi.radio.android.core.ui.SopifyScreenNavigation
import com.yazantarifi.radio.api.SpotifyAuthManager
import com.yazantarifi.radio.models.SpotifyAuthResponse
import com.yazantarifi.radio.useCases.GetAccessTokenUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RadioAuthScreen: AppCompatActivity() {

    @Inject
    lateinit var storage: SopifyStorageProvider

    @Inject
    lateinit var authUseCase: GetAccessTokenUseCase
    private val authManager by lazy { SpotifyAuthManager() }
    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, RadioAuthScreen::class.java))
            context.finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_auth)
        val loadingView = findViewById<ProgressBar>(R.id.loadingView)
        findViewById<WebView?>(R.id.login_webView)?.let {
            it.settings.let { settings ->
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowContentAccess = true
                settings.loadsImagesAutomatically = true
                settings.javaScriptCanOpenWindowsAutomatically = true
            }

            it.webViewClient = object: WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    println("UIUI :: Url : ${request?.url.toString()}")
                    if (authManager.isAccessTokenUrl(request?.url?.toString() ?: "")) {
                        val accessToken = authManager.getAccessTokenByUrl(request?.url?.toString() ?: "")
                        storage.insertAccessToken(accessToken)
                        view?.visibility = View.GONE
                        onRequestApiAccessToken(accessToken, loadingView)
                    }
                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    loadingView?.visibility = View.GONE
                }
            }
            it.loadUrl(authManager.getAuthLoginUrl())

            onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (it.canGoBack()) {
                        it.goBack()
                    } else {
                        finish()
                    }
                }
            })
        }
    }

    private fun onRequestApiAccessToken(accessToken: String, loadingView: View) {
        authUseCase.execute(accessToken, object: SopifyUseCaseListener {
            override fun onStateUpdated(newState: SopifyState) {
                when (newState) {
                    is SopifyState.SopifyEmptyState -> {}
                    is SopifyState.SopifyErrorState -> onShowErrorMessage(newState.exception.message ?: "Unexpected Error")
                    is SopifyState.SopifySuccessState -> onAccountLoggedInMessage(newState.payload as SpotifyAuthResponse)
                    is SopifyState.SopifyLoadingState -> lifecycleScope.launch(Dispatchers.Main) {
                        loadingView.visibility = if (newState.isLoading) View.VISIBLE else View.GONE
                    }
                }
            }
        })
    }

    private fun onAccountLoggedInMessage(info: SpotifyAuthResponse) {
        storage.insertAccessToken(info.accessToken ?: "")
        storage.updateLoggedInUser(true)
        Snackbar.make(
            findViewById(android.R.id.content),
            RadioApplicationMessages.getMessage("login_message_success"),
            Snackbar.LENGTH_SHORT
        ).show()
        Handler(Looper.getMainLooper()).postDelayed({
            SopifyScreenNavigation.startScreen(this@RadioAuthScreen, SopifyScreenNavigation.HOME_SCREEN)
            finish()
        }, 1000)
    }

    private fun onShowErrorMessage(message: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@RadioAuthScreen, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        authUseCase.clear()
    }

}
