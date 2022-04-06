package com.lentatykalentarunewapp.presentation

import androidx.lifecycle.ViewModel
import com.lentatykalentarunewapp.common.State
import com.lentatykalentarunewapp.domain.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<State<News>>
    val state: StateFlow<State<News>> = _state.asStateFlow()

    init {
        //init stateFlow here
    }

    fun getNews(){

    }
}