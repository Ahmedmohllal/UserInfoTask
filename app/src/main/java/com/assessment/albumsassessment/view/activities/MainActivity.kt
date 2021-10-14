package com.assessment.albumsassessment.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.assessment.albumsassessment.R
import com.assessment.albumsassessment.core.wrapper.DataStatus
import com.assessment.albumsassessment.viewmodel.UserInfoViewModel
import com.assessment.domain.model.album.AlbumsInfo
import com.assessment.domain.model.user.UserInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}