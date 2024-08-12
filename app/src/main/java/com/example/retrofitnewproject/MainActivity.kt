package com.example.retrofitnewproject

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitnewproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val apiKey = "0005c1080a875a8711f0c93902191164"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchTopHeadlines()

    }

    private fun fetchTopHeadlines() {
        val apiService = RetrofitClient.apiService
        apiService.getTopHeadlines("en", "us", apiKey).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    newsAdapter = NewsAdapter(articles) { selectedArticle ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra("title", selectedArticle.title)
                            putExtra("description", selectedArticle.description)
                            putExtra("publishedAt", selectedArticle.publishedAt)
                            putExtra("content", selectedArticle.content)
                            putExtra("image", selectedArticle.image)
                            putExtra("url", selectedArticle.url)
                            putExtra("author", selectedArticle.source.name)                         }
                        startActivity(intent)
                    }
                    binding.recyclerView.adapter = newsAdapter
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            }
        })
    }
}
