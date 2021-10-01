package com.example.training.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.training.databinding.ItemPhotoBinding
import com.example.training.models.picture

class GalleryAdapter (private val listener: onItemClickListener):
    PagingDataAdapter<picture, GalleryAdapter.PictureViewHolder>(PHOTO_COMPARATOR) {

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPhotoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

  inner  class PictureViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

init {
 binding.root.setOnClickListener{

     val position = bindingAdapterPosition
     if(position!= RecyclerView.NO_POSITION){
         val item = getItem(position)
         if(item!=null)
         listener.onItemClick(item)
     }

 }
}
        fun bind(photo: picture) {
            binding.apply {

                Glide.with(itemView).load(photo.url).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade()).error(
                    com.google.android.material.R.drawable.mtrl_ic_error
                ).into(imageView)

                username.text = photo.author

            }
        }
    }

    interface onItemClickListener{
        fun onItemClick(photo: picture)
    }

    companion object {

        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<picture>() {
            override fun areItemsTheSame(oldItem: picture, newItem: picture) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: picture, newItem: picture) =
                oldItem == newItem
        }

    }
}
