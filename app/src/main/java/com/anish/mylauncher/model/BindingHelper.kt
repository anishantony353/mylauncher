package com.anish.mylauncher.model

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingHelper {

    //var options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImageView(iv:ImageView, drawable:Drawable){
        Glide.with(iv.context).load(drawable).into(iv)
    }

}