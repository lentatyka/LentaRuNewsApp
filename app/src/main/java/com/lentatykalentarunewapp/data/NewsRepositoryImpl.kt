package com.lentatykalentarunewapp.data

import com.lentatykalentarunewapp.common.Constants
import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.data.network.dto.NewsDto
import com.lentatykalentarunewapp.domain.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getNews(): NewsDto {
        TODO()
    }
}