package com.example.retrofitnewproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("lang") language: String,
        @Query("country") country: String,
        @Query("token") apiKey: String
    ): Call<NewsResponse>
}
