package com.lentatykalentarunewapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.common.wrapEspressoIdlingResource
import com.lentatykalentarunewapp.domain.GetNewsUseCase
import com.lentatykalentarunewapp.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<State<News>> = MutableStateFlow(State.Loading)
    val state: StateFlow<State<News>> = _state.asStateFlow()

    init {
        //when init State.Loading will be duplicated. Bad idea... What about
        // MutableSharedFlow (replace = 1, onBuff = DROP.OlDEST) No Need init value
        getNews()
    }

    fun getNews() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                getNewsUseCase().collect {
                    _state.value = it
                }
            }
        }
    }
}