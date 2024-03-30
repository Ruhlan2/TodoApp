package com.example.todoapp.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.common.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

abstract class BaseViewModel<STATE:State>:ViewModel() {

    private val _state=MutableLiveData<STATE>()
    val state:LiveData<STATE> =_state


    fun setState(state: STATE){
        _state.value=state
    }


    inline fun <T> Flow<Resource<T>>.handleResult(
        crossinline onComplete :(T) ->Unit,
        crossinline onError :(Exception) ->Unit,
        crossinline onLoading:()->Unit
    ){
        onEach {response->
            when(response){
                is Resource.Error->{
                    onError(response.throwable)
                }
                is Resource.Loading->onLoading()
                is Resource.Success->{
                    response.result?.let{onComplete(it)}
                }
            }
        }.launchIn(viewModelScope)
    }

}

interface State