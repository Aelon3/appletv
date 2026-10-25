package com.aelon.appletv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import org.json.JSONObject

class MainActivity : Activity() {
    private lateinit var web: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        web = WebView(this)
        web.setBackgroundColor(Color.BLACK)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.mediaPlaybackRequiresUserGesture = false
        web.webViewClient = object : WebViewClient() {
            // Keep the launcher on its own page; never navigate away to the web.
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean =
                !request.url.toString().startsWith("file:///android_asset/")
        }
        web.addJavascriptInterface(Bridge(this, web), "Android")
        setContentView(web)
        web.requestFocus()
        web.loadUrl("file:///android_asset/index.html")

        NotifService.listener = { app, title, text ->
            runOnUiThread {
                web.evaluateJavascript(
                    "window.onNotify&&onNotify(${JSONObject.quote(app)},${JSONObject.quote(title)},${JSONObject.quote(text)})",
                    null
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Home pressed while the launcher is already showing: close any open panel.
        if (intent.hasCategory(Intent.CATEGORY_HOME)) web.evaluateJavascript("window.atvHome&&atvHome()", null)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        // A launcher never exits on Back; the page decides what Back does.
        web.evaluateJavascript("window.atvBack&&window.atvBack()", null)
    }

    @Suppress("DEPRECATION")
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onResume() { super.onResume(); web.onResume() }
    override fun onPause() { web.onPause(); super.onPause() }
    override fun onDestroy() {
        NotifService.listener = null
        web.destroy()
        super.onDestroy()
    }
}
