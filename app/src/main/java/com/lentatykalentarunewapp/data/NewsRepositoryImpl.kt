package com.lentatykalentarunewapp.data

import com.lentatykalentarunewapp.common.Constants
import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.domain.NewsRepository
import com.lentatykalentarunewapp.domain.model.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepositoryImpl(
) : NewsRepository {
    private val mapper: Mapper = Mapper()
    private val newsApi: NewsApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)
    override suspend fun getNews(): News {
        return mapper.mapNewsDtoToNews(newsApi.getNews(Constants.API_KEY))
    }
}