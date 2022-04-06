package com.lentatykalentarunewapp.data

import com.lentatykalentarunewapp.domain.NewsRepository
import com.lentatykalentarunewapp.domain.model.News

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getNews(): News {
        TODO()
    }
}