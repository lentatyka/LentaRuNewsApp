package com.lentatykalentarunewapp.domain


import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.domain.model.News
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.HttpException
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
class GetNewsUseCaseTest {

    private lateinit var newsUseCase: GetNewsUseCase
    private val fakeNewsRepository = mock(NewsRepository::class.java)

    @Before
    fun setUp() {
        newsUseCase = GetNewsUseCase(fakeNewsRepository)
    }

    @Test
    fun `flow emits success`() = runTest {
        val news = News(emptyList())
        Mockito.`when`(fakeNewsRepository.getNews()).thenReturn(news)

        val flow = newsUseCase.invoke()

        flow.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            val actual = awaitItem()
            assertThat(actual is State.Success).isTrue()
            awaitComplete()
            assertThat((actual as State.Success).result).isEqualTo(News(emptyList()))
        }
    }

    @Test
    fun `flow emits error`() = runTest {
        Mockito.`when`(fakeNewsRepository.getNews()).thenThrow(HttpException::class.java)

        val flow = newsUseCase.invoke()

        flow.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            val actual = awaitItem()
            assertThat(actual is State.Error).isTrue()
            awaitComplete()
            assertThat((actual as State.Error).message).isEqualTo("An unexpected error occurred")
        }
    }
}