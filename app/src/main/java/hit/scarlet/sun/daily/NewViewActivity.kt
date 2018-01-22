package hit.scarlet.sun.daily

import android.util.Log
import android.view.KeyEvent
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_new_view.*

class NewViewActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_new_view

    override fun init() {
        initSwipe()
        initWebView()
    }

    private fun initWebView() {
        news_web_view.webViewClient = WebViewClient()
        val webSettings = news_web_view.settings
        webSettings.javaScriptEnabled = true
        news_web_view.loadUrl("http://daily.zhihu.com/story/" + intent.extras.getString("newsId")!!)
        Log.d(TAG, "initWebview: " + news_web_view.url)
    }

    private fun initSwipe() {
        news_container.setOnRefreshListener {
            Log.d(TAG, "initSwipe: yes!")
            news_web_view.reload()
            news_container.isRefreshing = false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && news_web_view.canGoBack()) {
            news_web_view.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    companion object {

        private val TAG = "NewViewActivity"
    }
}
