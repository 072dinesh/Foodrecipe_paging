package com.example.foodrecipipaging.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

import com.example.foodrecipipaging.R


//@BindingAdapter("app:loadImage")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri)
//    }
//}


@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, url: String){
    imageView.load(url){
        crossfade(true)
        placeholder(R.drawable.ic_launcher_background)
        error(R.drawable.ic_launcher_background)
    }
}

//
//@BindingAdapter("app:loadCircleImage")
//fun loadCircleImage(imageView: ImageView, url: String){
//    imageView.load(url){
//        transformations(CircleCropTransformation())
//        crossfade(true)
//        placeholder(R.drawable.ic_baseline_image_24)
//        error(R.drawable.ic_baseline_broken_image_24)
//    }
//}