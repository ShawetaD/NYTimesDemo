package com.app.nytimes.ui.common.binding

import android.view.View
import android.webkit.WebView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

}

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .circleCrop()
            .into(view)
    }

}

@BindingAdapter("loadUrl")
fun loadWebURL(view: WebView, url: String?) {
    if (!url.isNullOrEmpty()) {

    }

}
