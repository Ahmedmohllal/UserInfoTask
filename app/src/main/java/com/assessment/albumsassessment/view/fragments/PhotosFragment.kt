package com.assessment.albumsassessment.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.assessment.albumsassessment.R
import com.assessment.albumsassessment.core.wrapper.DataStatus
import com.assessment.albumsassessment.view.activities.ImageViewerActivity
import com.assessment.albumsassessment.view.adapters.PhotosRecyclerAdapter
import com.assessment.albumsassessment.viewmodel.AlbumPhotosViewModel
import com.assessment.domain.model.photo.PhotosItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.fragment_user_profile.*
import kotlinx.android.synthetic.main.fragment_user_profile.no_data_found
import kotlinx.android.synthetic.main.fragment_user_profile.progress_loading


@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val args: PhotosFragmentArgs by navArgs()
    private lateinit var albumId: String
    private lateinit var albumName: String

    private val albumPhotosViewModel: AlbumPhotosViewModel by viewModels()
    private val photosList = ArrayList<PhotosItem>()
    private lateinit var photosRecyclerAdapter: PhotosRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photos, container, false)
        albumId = args.albumId.toString()
        albumName = args.albumName
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_title.text = albumName

        search_et.findViewById<EditText>(R.id.search_et)
            .addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    val filterText = charSequence.toString().trim()
                    photosRecyclerAdapter.filter!!.filter(filterText)
                }

                override fun afterTextChanged(editable: Editable) {}
            })

        initRecyclerView()
        getAlbumPhotos()

    }

    private fun initRecyclerView() {
        photos_rv.apply {
            this.layoutManager = GridLayoutManager(activity, 3)
            photosRecyclerAdapter = PhotosRecyclerAdapter(
                photosList, photosList,
                {
                    openImageViewerActivity(it)
                }, context
            )
            this.adapter = photosRecyclerAdapter

        }
    }

    private fun openImageViewerActivity(imgUrl: String) {
        startActivity(
            Intent(
                context,
                ImageViewerActivity::class.java
            ).putExtra("ImageUrl", imgUrl)
        )
    }

    private fun getAlbumPhotos() {
        albumPhotosViewModel.getPhotos(albumId)
        albumPhotosViewModel.photosInfoLiveData.observe(viewLifecycleOwner, {
            when (it?.status) {
                DataStatus.Status.LOADING -> showLoadingProgress()
                DataStatus.Status.SUCCESS -> handleSuccessData(it)
                DataStatus.Status.ERROR -> showError()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleSuccessData(photoItem: DataStatus<List<PhotosItem>>?) {
        hideLoadingProgress()
        photoItem?.data?.toList()?.let {
            photosList.addAll(it)
            photosRecyclerAdapter.notifyDataSetChanged()
        }

    }

    private fun showLoadingProgress() {
        progress_loading.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        progress_loading.visibility = View.INVISIBLE
    }

    private fun showError() {
        hideLoadingProgress()
        user_info_layout.visibility = View.INVISIBLE
        no_data_found.visibility = View.VISIBLE
    }


}