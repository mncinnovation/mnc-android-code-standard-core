package com.mncgroup.core.ui

import android.os.Bundle

class WebViewActivity : BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPageTitle(title)
        if (headers == null) {
            loadPageByUrl(url)
        } else {
            headers?.let {
                loadPageByUrl(url, it)
            }
        }
    }
}