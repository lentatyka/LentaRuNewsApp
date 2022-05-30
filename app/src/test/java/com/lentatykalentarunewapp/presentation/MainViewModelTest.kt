package com.lentatykalentarunewapp.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.lentatykalentarunewapp.MainCoroutineRule
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.domain.GetNewsUseCase
import com.lentatykalentarunewapp.domain.NewsRepository
import com.lentatykalentarunewapp.domain.model.Article
import com.lentatykalentarunewapp.domain.model.News
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.HttpException
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val fakeRepo = mock(NewsRepository::class.java)
    private lateinit var mainViewModel:MainViewModel

    @get:Rule
    var mainDispatcherRule = MainCoroutineRule()
//    //
    @Before
    fun setUp() {
        mainViewModel = MainViewModel(GetNewsUseCase(fakeRepo))
    }

    @Test
    fun `stateflow returned success state with emptylist news`() = runTest{
        val news = News(emptyList())
        Mockito.`when`(fakeRepo.getNews()).thenReturn(news)
            mainViewModel.getNews()
        mainViewModel.state.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            val actual = awaitItem()
            assertThat(actual is State.Success).isTrue()
            assertThat((actual as State.Success).result).isEqualTo(news)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `stateflow returned success state news not equals`() = runTest{
        val emptyNews = News(emptyList())
        val news = News(listOf(Article("", "", "", "")))
        Mockito.`when`(fakeRepo.getNews()).thenReturn(news)
        mainViewModel.getNews()
        mainViewModel.state.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            val actual = awaitItem()
            assertThat(actual is State.Success).isTrue()
            assertThat((actual as State.Success).result).isNotEqualTo(emptyNews)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `stateflow returned error state with http exception`() = runTest{
        val error = "An unexpected error occurred"
        Mockito.`when`(fakeRepo.getNews()).thenThrow(HttpException::class.java)
        mainViewModel.getNews()
        mainViewModel.state.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            val actual = awaitItem()
            assertThat(actual is State.Error).isTrue()
            assertThat((actual as State.Error).message).isEqualTo(error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}