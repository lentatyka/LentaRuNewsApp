package com.lentatykalentarunewapp.domain

import com.lentatykalentarunewapp.data.network.dto.NewsDto

interface NewsRepository {
    suspend fun getNews(): NewsDto
}