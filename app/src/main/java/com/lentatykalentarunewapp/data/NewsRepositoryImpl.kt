package com.lentatykalentarunewapp.data

import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.domain.NewsRepository
import com.lentatykalentarunewapp.domain.model.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val mapper: Mapper,
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(): News {
        return mapper.mapNewsDtoToNews(newsApi.getNews())
    }
}