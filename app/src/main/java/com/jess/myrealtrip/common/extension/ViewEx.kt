package com.jess.myrealtrip.common.extension

import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.common.view.webview.WebView

/**
 * Ripple 사각형
 */
fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        foreground = ContextCompat.getDrawable(context, resourceId)
    } else {
        setBackgroundResource(resourceId)
    }
}

/**
 * Ripple 원형
 */
fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        foreground = ContextCompat.getDrawable(context, resourceId)
    } else {
        setBackgroundResource(resourceId)
    }
}

/**
 * 이미지 로드
 */
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .placeholder(R.drawable.shp_rect_f5f5f5_r12)
        .error(R.drawable.shp_rect_f5f5f5_r12)
        .apply(
            RequestOptions().transform(
                CenterCrop(), RoundedCorners(
                    context.resources.getDimensionPixelSize(
                        R.dimen.dp12
                    )
                )
            )
        )
        .into(this)
}

/**
 * Toolbar Title Setting
 */
@BindingAdapter("toolbarTitle")
fun Toolbar.setToolbarTitle(title: String?) {
    tryCatch {
        if (title.isNullOrEmpty()) {
            return
        }
        this.title = title
    }
}

/**
 * Toolbar Title Setting
 */
@BindingAdapter("loadUrl")
fun WebView.loadUrl(url: String?) {
    tryCatch {
        if (url.isNullOrEmpty()) {
            return
        }
        this.loadUrl(url)
    }
}