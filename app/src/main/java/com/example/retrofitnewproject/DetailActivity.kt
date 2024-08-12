package com.example.retrofitnewproject

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofitnewproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val publishedAt = intent.getStringExtra("publishedAt")
        val content = intent.getStringExtra("content")
        val imageUrl = intent.getStringExtra("image")
        val url = intent.getStringExtra("url")
        val author = intent.getStringExtra("author")

        binding.detailTitle.text = title
        binding.detailDescription.text = description
        binding.detailPublishedAt.text = publishedAt
        binding.detailContent.text = content
        binding.detailAuthor.text = author

        binding.detailUrl.text = url
        binding.detailUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        Glide.with(this)
            .load(imageUrl)
            .into(binding.detailImage)
    }
}
