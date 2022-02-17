package com.dashrath.kotlinrepositorypattern.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BindingUtils {

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(imageView: AppCompatImageView, url: String?) {
            Glide.with(imageView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView)
        }
    }
}