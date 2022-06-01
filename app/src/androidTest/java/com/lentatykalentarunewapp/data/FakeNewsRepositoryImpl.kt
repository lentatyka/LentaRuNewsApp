package com.lentatykalentarunewapp.data

import com.lentatykalentarunewapp.domain.NewsRepository
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import javax.inject.Inject

class FakeNewsRepositoryImpl @Inject constructor() : NewsRepository {

    override suspend fun getNews() = News(articlesList)

    companion object{
        val articlesList = listOf(
            Article("10:10:10", "News 1", null, null),
            Article("20:20:20", "News 2", null, null),
            Article("30:30:30", "News 3", null, null)
        )
    }
}