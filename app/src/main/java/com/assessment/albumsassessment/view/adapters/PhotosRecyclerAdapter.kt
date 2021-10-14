package com.assessment.albumsassessment.view.adapters

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.assessment.albumsassessment.R
import com.assessment.domain.model.photo.PhotosItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*
import kotlin.collections.ArrayList

class PhotosRecyclerAdapter(
    private var photosList: ArrayList<PhotosItem>,
    private var photoTempList: ArrayList<PhotosItem>,
    private val itemClick : (String)-> Unit,
    private val context: Context
) : RecyclerView.Adapter<PhotosRecyclerAdapter.ViewHolder>(), Filterable {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(photosList[position].thumbnailUrl)
            .into(holder.photoIv)

    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        var photoIv: ImageView = view.photo
        init {
            view.setOnClickListener {
                itemClick.invoke(photosList[adapterPosition].thumbnailUrl)
            }
        }
    }

    override fun getFilter(): Filter? {
        return categoryFilter
    }

    private val categoryFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filteredList: ArrayList<PhotosItem> = ArrayList()

            if (charSequence.isEmpty() || charSequence == null) {
                filteredList.addAll(photoTempList)
            } else {
                val filterPattern = charSequence.toString().trim()
                for (item in photosList) {
                    if (item.title.contains(filterPattern)
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            photosList = filterResults.values as ArrayList<PhotosItem>
            notifyDataSetChanged()
        }
    }
}