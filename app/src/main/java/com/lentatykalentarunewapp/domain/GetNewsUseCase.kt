package com.lentatykalentarunewapp.domain

import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<State<News>> {
        return flow {
            try {
                emit(State.Loading)
                val news = repository.getNews()
                emit(State.Success(news))
            } catch (e: HttpException) {
                emit(State.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(
                    State.Error(
                        e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection!"
                    )
                )
            }
        }
    }
}