package com.lentatykalentarunewapp.presentation

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "loading", "error")
fun loadImage(view: ImageView, url: String, errorImage: Drawable, loadImage: Drawable){
    Glide.with(view.context)
        .load(url)
        .placeholder(loadImage)
        .error(errorImage)
        .into(view)
}