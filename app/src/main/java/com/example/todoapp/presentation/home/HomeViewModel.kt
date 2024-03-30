package com.example.todoapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.todoapp.common.base.BaseViewModel
import com.example.todoapp.common.base.State
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.domain.useCase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase:NoteUseCase
) : BaseViewModel<HomeUiState>() {


     fun getAllData(){
        viewModelScope.launch {
            useCase.getAll().handleResult(
                onComplete = {
                    setState(HomeUiState.Success(it))
                },
                onLoading = {
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setState(HomeUiState.Failure(it.localizedMessage as String))
                }
            )
        }
    }

    fun insertData(list: List<NoteEntity>){
        viewModelScope.launch {
            useCase.insertNote(list)
            getAllData()
        }
    }
}

sealed class HomeUiState:State{

    data class Success(val list:List<NoteUiModel>):HomeUiState()

    data class Failure(val message:String):HomeUiState()

    data object Loading:HomeUiState()
}