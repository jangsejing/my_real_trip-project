package com.jess.myrealtrip.common.view.webview

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jess.myrealtrip.common.util.tryCatch

/**
 * @author 장세진
 * @description WebChromeClient for WebView
 */
class WebBaseViewClient(private val context: Context?) : WebViewClient() {

    private var onPageFinishedListener: (() -> Unit)? = null

    fun setOnPageFinishedListener(listener: (() -> Unit)?) {
        onPageFinishedListener = listener
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        onPageFinishedListener?.invoke()
        super.onPageFinished(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return shouldOverrideUrlLoading(view, request.url.toString())
    }

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        tryCatch {
            AlertDialog.Builder(context).run {
                setTitle("이 사이트의 보안 인증서는 신뢰할 수 없습니다.\n계속 진행하시겠습니까?")
                setPositiveButton(
                    context.getString(android.R.string.ok)
                ) { dialog, which ->
                    handler.proceed()
                }
                setNegativeButton(
                    context.getString(android.R.string.cancel)
                ) { dialog, which ->
                    handler.cancel()
                }
                setCancelable(false)
                show()
            }

        }
    }
}
