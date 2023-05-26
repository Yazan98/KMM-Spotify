package com.yazantarifi.radio.android.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider
import com.yazantarifi.radio.api.RedditAuthManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RadioAuthScreen: AppCompatActivity() {

    @Inject
    lateinit var storage: SopifyStorageProvider
    private val authManager by lazy { RedditAuthManager() }
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
        loadingView.visibility = View.VISIBLE

    }

}
