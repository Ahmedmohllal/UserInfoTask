package com.assessment.albumsassessment.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assessment.albumsassessment.R
import com.assessment.domain.model.album.AlbumsInfo
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumsRecyclerAdapter(
    private val albumsList: ArrayList<AlbumsInfo>,
    private val context: Context,
) :
    RecyclerView.Adapter<AlbumsRecyclerAdapter.ViewHolder>() {

    lateinit var mListener: RecyclerViewOnClickListener

    fun setOnClickListener(listener: RecyclerViewOnClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.albumName.text = albumsList[position].title

    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var albumName: TextView = view.album_name

        init {
            view.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }
        }
    }
}