package com.lentatykalentarunewapp.data.network

import com.lentatykalentarunewapp.data.network.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines?sources=google-news-ru")
    suspend fun getNews(@Query("apiKey") key:String): NewsDto
}