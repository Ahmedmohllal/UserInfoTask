package com.assessment.albumsassessment.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assessment.albumsassessment.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewerActivity : AppCompatActivity() {
    lateinit var imgUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imgUrl = intent.getStringExtra("ImageUrl")!!
        setContentView(R.layout.activity_image_view)
        Picasso.get()
            .load(imgUrl)
            .into(photo_view)

        share_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_TEXT, imgUrl
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Send To"))
        }
    }
}