package com.mncgroup.core.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

class ScrollObservableWebView : WebView {

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet?) : super(ctx, attrs)
    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

    private var _scrollListener: OnScrollChangedCallback? = null

    fun setOnScrollChangedCallback(scrollChangedCallback: OnScrollChangedCallback?) {
        _scrollListener = scrollChangedCallback
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        _scrollListener?.invoke(l, t, oldl, oldt)
    }

}

typealias OnScrollChangedCallback = (scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) -> Unit