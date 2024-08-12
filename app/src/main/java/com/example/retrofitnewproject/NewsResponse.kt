package com.example.retrofitnewproject

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String?,
    val publishedAt: String?,
    val content: String?,
    val image: String?,
    val source: Source,
    val url: String?
)

data class Source(
    val name: String
)
