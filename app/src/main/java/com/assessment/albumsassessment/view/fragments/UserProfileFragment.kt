package com.assessment.albumsassessment.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.albumsassessment.R
import com.assessment.albumsassessment.core.wrapper.DataStatus
import com.assessment.albumsassessment.view.adapters.AlbumsRecyclerAdapter
import com.assessment.albumsassessment.view.adapters.RecyclerViewOnClickListener
import com.assessment.albumsassessment.viewmodel.UserInfoViewModel
import com.assessment.domain.model.album.AlbumsInfo
import com.assessment.domain.model.user.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_profile.*

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val userInfoViewModel: UserInfoViewModel by viewModels()
    private val albumsList = ArrayList<AlbumsInfo>()
    private lateinit var albumRecyclerAdapter: AlbumsRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getUserInfo()

    }

    private fun initRecyclerView() {
        albums_rv.apply {
            this.layoutManager = LinearLayoutManager(activity)
            albumRecyclerAdapter = AlbumsRecyclerAdapter(albumsList, context)
            this.adapter = albumRecyclerAdapter
            this.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun getUserInfo() {
        userInfoViewModel.getUserInfo()
        userInfoViewModel.userInfoLiveData.observe(viewLifecycleOwner,
            {
                when (it?.status) {
                    DataStatus.Status.LOADING -> showLoadingProgress()
                    DataStatus.Status.SUCCESS -> handleSuccessData(it)
                    DataStatus.Status.ERROR -> showError()
                }
            })
    }



    private fun handleSuccessData(userInfo: DataStatus<List<UserInfo>>?) {
        hideLoadingProgress()
        user_name.text = userInfo?.data?.get(0)?.name
        user_address.text =
            userInfo?.data?.get(0)?.address?.street
                .plus(", ")
                .plus(userInfo?.data?.get(0)?.address?.suite)
                .plus(", ")
                .plus(userInfo?.data?.get(0)?.address?.city)
                .plus(", ")
                .plus(userInfo?.data?.get(0)?.address?.zipcode)

        getUserAlbums()
    }

    private fun getUserAlbums() {
        userInfoViewModel.userAlbumsLiveData.observe(viewLifecycleOwner, {
            handleAlbumsData(it)
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleAlbumsData(albumsData: DataStatus<List<AlbumsInfo>>?) {
        albumsList.clear()
        albumsData?.data?.toList()?.let { albumsList.addAll(it) }
        albumRecyclerAdapter.notifyDataSetChanged()
        albumRecyclerAdapter.setOnClickListener(object : RecyclerViewOnClickListener {
            override fun onItemClick(position: Int) {
                    goToPhotosFragment(position)
            }
        })


    }

    private fun goToPhotosFragment(position: Int) {
        val albumId = albumsList[position].id
        val albumName = albumsList[position].title
        val action = UserProfileFragmentDirections.actionUserProfileFragmentToPhotosFragment(albumId,albumName)
        Navigation.findNavController(this.requireActivity(),R.id.fragmentContainerView).navigate(action)
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