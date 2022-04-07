package com.lentatykalentarunewapp.domain.model

data class Article(
    val author: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
