package com.lentatykalentarunewapp.presentation

import androidx.lifecycle.ViewModel
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.domain.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val getNewsUseCase: GetNewsUseCase
    ) : ViewModel() {
    private val _state: MutableStateFlow<State<News>> = MutableStateFlow(State.Loading)
    val state: StateFlow<State<News>> = _state.asStateFlow()

    init {
        //when init State.Loading will be duplicated. Bad idea... What about
        // MutableSharedFlow (replace = 1, onBuff = DROP.OlDEST) No Need init value
        getNews()
    }

    fun getNews(){
        getNewsUseCase().onEach {
            _state.value = it
        }.launchIn(viewModelScope)
        }
}