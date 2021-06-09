package com.useflikrapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.useflikrapp.R
import com.useflikrapp.domain.Photo
import kotlinx.android.synthetic.main.photos_row.view.*

class ShowPhotosAdapter(val photosList: MutableList<Photo> = mutableListOf()) : RecyclerView.Adapter<ShowPhotosAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photos_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {

            Picasso.get().
            load(photo.url)
                .resize(SIZE, SIZE)
                .centerCrop()
                .into(itemView.imageView)
        }
    }
}

const val SIZE = 400