package com.example.newsapp.utils

import android.text.TextPaint
import android.text.style.URLSpan

class URLSpanUnderline(url: String): URLSpan(url) {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}