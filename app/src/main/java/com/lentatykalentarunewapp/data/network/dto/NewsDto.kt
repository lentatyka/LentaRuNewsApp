package com.lentatykalentarunewapp.data.network.dto

data class NewsDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
