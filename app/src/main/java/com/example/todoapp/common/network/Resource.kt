package com.example.todoapp.common.network

import java.lang.Exception

sealed class Resource <out T>{

    data object Loading:Resource<Nothing>()

    data class Success<out T>(val result:T?):Resource<T>()
    data class Error(val throwable: Exception):Resource<Nothing>()
}