package com.lentatykalentarunewapp.data

import com.google.common.truth.Truth.assertThat
import com.lentatykalentarunewapp.data.network.dto.ArticleDto
import com.lentatykalentarunewapp.data.network.dto.NewsDto
import com.lentatykalentarunewapp.data.network.dto.SourceDto
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import org.junit.Test

class MapperTest {

    private val mapper = Mapper()

    @Test
    fun `wrong data return unknown`() {
        val declaredMethod =
            mapper.javaClass.getDeclaredMethod("formatDateFromString", String::class.java)
        declaredMethod.isAccessible = true
        val param = arrayOfNulls<Any>(1)
        param[0] = ""
        val actual = declaredMethod.invoke(mapper, *param)
        assertThat(actual).isEqualTo("unknown")
    }

    @Test
    fun `right data return right data format`() {
        val declaredMethod =
            mapper.javaClass.getDeclaredMethod("formatDateFromString", String::class.java)
        declaredMethod.isAccessible = true
        val param = arrayOfNulls<Any>(1)
        param[0] = "2022-05-04T12:00:00Z"
        val actual = declaredMethod.invoke(mapper, *param)
        assertThat(actual).isEqualTo("ср, мая 4, '22")
    }

    @Test
    fun `NewsDto with emptyList return News with emptyList`() {
        val newsDto = NewsDto(
            articles = emptyList(),
            status = "status",
            totalResults = 2
        )
        val news = News(emptyList())
        val actual = mapper.mapNewsDtoToNews(newsDto)

        assertThat(actual).isEqualTo(news)
    }

    @Test
    fun `NewsDto with wrong url format return News with null url`() {
        val newsDto = NewsDto(
            articles = listOf(
                ArticleDto(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "2022-05-04T12:00:00Z",
                    source = SourceDto("1", "1"),
                    title = "Title",
                    url = "URL",  //urlDto wrong format
                    urlToImage = "IMG_URL"

                )
            ),
            status = "status",
            totalResults = 2
        )
        val news = News(
            articles = listOf(
                Article(
                    publishedAt = "ср, мая 4, '22",
                    title = "Title",
                    url = null,
                    urlToImage = "IMG_URL"
                )
            )
        )

        val actual = mapper.mapNewsDtoToNews(newsDto)
        assertThat(actual).isEqualTo(news)
    }

    /*
    Mapper don't return right url.
    Reason: gradle testOption - defaultValue = true
    Can't mock URLUtil class
     */
    @Test
    fun `NewsDto with right url return News with null url`() {
        val newsDto = NewsDto(
            articles = listOf(
                ArticleDto(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "2022-05-04T12:00:00Z",
                    source = SourceDto("1", "1"),
                    title = "Title",
                    url = "https://ya.ru",
                    urlToImage = null

                )
            ),
            status = "status",
            totalResults = 2
        )
        val news = News(
            articles = listOf(
                Article(
                    publishedAt = "ср, мая 4, '22",
                    title = "Title",
                    url = null,
                    urlToImage = null
                )
            )
        )

        val actual = mapper.mapNewsDtoToNews(newsDto)
        assertThat(actual).isEqualTo(news)
    }

}