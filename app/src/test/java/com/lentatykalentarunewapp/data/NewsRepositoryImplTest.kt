package com.lentatykalentarunewapp.data

import com.google.common.truth.Truth.assertThat
import com.lentatykalentarunewapp.data.network.NewsApi
import com.lentatykalentarunewapp.data.network.dto.ArticleDto
import com.lentatykalentarunewapp.data.network.dto.NewsDto
import com.lentatykalentarunewapp.data.network.dto.SourceDto
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class NewsRepositoryImplTest{

    private val newsApi = mock(NewsApi::class.java)
    private val mapper = Mapper()
    private val repository = NewsRepositoryImpl(mapper, newsApi)


    @Test
    fun `repository returned News with empty list`() = runTest{
       Mockito.`when`(newsApi.getNews()).thenReturn(NewsDto(emptyList(), "", 0))
        val actual = repository.getNews()
        val news = News(emptyList())
        assertThat(actual).isEqualTo(news)
    }
    @Test
    fun `repository returned News with equals articles`() = runTest{
        val newsDto = NewsDto(
            articles = listOf(
                ArticleDto(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "2022-05-04T12:00:00Z",
                    source = SourceDto("1", "1"),
                    title = "Title",
                    url = "URL",
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
                    url = "URL",
                    urlToImage = ""
                )
            )
        )
        Mockito.`when`(newsApi.getNews()).thenReturn(newsDto)
        val actual = repository.getNews()
        assertThat(actual).isEqualTo(news)
    }
}