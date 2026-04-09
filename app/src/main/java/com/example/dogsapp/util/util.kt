package com.example.dogsapp.util

import android.R
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadFromUrl(url: String?,progressDrawable: CircularProgressDrawable){


    val options= RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_lock_lock)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }

}

@BindingAdapter("android:download_url")
fun downloadUrl(view: ImageView,url: String?){
    view.downloadFromUrl(url,placeHolderProgressBar(view.context))

}