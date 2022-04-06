package com.lentatykalentarunewapp.common

sealed class State<out R> {
    class Success<out T>(val result: T): State<T>()
    class Error(val message: String): State<Nothing>()
    object Loading : State<Nothing>()
}