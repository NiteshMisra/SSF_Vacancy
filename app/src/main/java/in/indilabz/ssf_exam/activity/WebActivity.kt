package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.utils.Constants.Companion.PROFILE_URL
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import im.delight.android.webview.AdvancedWebView

class WebActivity : AppCompatActivity() {

    private lateinit var web: AdvancedWebView
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        toolbar = findViewById(R.id.toolbar)

        web = findViewById(R.id.webView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            web.settings.setAllowFileAccessFromFileURLs(true);
            web.settings.setAllowUniversalAccessFromFileURLs(true);
        }

        web.loadUrl(PROFILE_URL+INDIPreferences.user()!!.id)

        toolbar.setNavigationOnClickListener{
            this.finish()
        }

        Log.d("TAG_URL", PROFILE_URL+INDIPreferences.user()!!.id)
    }
}
