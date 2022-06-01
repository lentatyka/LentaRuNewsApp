package com.lentatykalentarunewapp.data

import android.annotation.SuppressLint
import android.webkit.URLUtil
import com.lentatykalentarunewapp.data.network.dto.NewsDto
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class Mapper @Inject constructor(){
    fun mapNewsDtoToNews(newsDto: NewsDto) = News(
        articles = newsDto.articles.map {
            Article(
                publishedAt = formatDateFromString(it.publishedAt),
                title = it.title,
                url = if (URLUtil.isValidUrl(it.url)) it.url else null,
                urlToImage = it.urlToImage
            )
        }
    )

    @SuppressLint("SimpleDateFormat")
    private fun formatDateFromString(dateString: String):String{
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val formatter = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
            formatter.format(parser.parse(dateString))
        }catch (e: ParseException){
            "unknown"
        }
    }
}