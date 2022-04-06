package com.lentatykalentarunewapp.domain.model

import com.lentatykalentarunewapp.data.network.dto.SourceDto

data class Article(
    val author: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
