package com.example.newsapp.data

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: Any,
    val publishedAt: String,
    val content: String
)