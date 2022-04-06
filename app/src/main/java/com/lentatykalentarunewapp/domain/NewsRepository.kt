package com.lentatykalentarunewapp.domain

import com.lentatykalentarunewapp.domain.model.News

interface NewsRepository {
    suspend fun getNews(): News
}