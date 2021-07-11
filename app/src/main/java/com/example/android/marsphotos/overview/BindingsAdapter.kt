package com.example.android.marsphotos.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.R
import com.example.android.marsphotos.network.MarsPhoto

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imageUrl:String?){
    imageUrl?.let {
        val imageUri=imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecycler(recyclerView: RecyclerView,data:List<MarsPhoto>?){
    val adapter=recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,status: MarsApiStatus?){
    when(status){
        MarsApiStatus.LOADING->{
            statusImageView.visibility=View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE->{
            statusImageView.visibility=View.GONE
        }
    }
}