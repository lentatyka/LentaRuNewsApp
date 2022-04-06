package com.lentatykalentarunewapp.data

import android.annotation.SuppressLint
import com.lentatykalentarunewapp.data.network.dto.NewsDto
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import java.text.SimpleDateFormat


class Mapper {
    fun mapNewsDtoToNews(newsDto: NewsDto) = News(
        articles = newsDto.articles.map {
            Article(
                author = it.author,
                publishedAt = formatDateFromString(it.publishedAt),
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
    )

    @SuppressLint("SimpleDateFormat")
    private fun formatDateFromString(dateString: String):String{
        return SimpleDateFormat("EEE, MMM d, ''yy").format(dateString)
    }
}