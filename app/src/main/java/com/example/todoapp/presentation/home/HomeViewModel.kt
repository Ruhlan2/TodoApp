package com.example.todoapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.todoapp.R
import com.example.todoapp.common.base.BaseViewModel
import com.example.todoapp.common.base.State
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.domain.useCase.CheckNoteUseCase
import com.example.todoapp.domain.useCase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase:NoteUseCase,
    private val checkNoteUseCase: CheckNoteUseCase
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

    private fun insertData(list: List<NoteEntity>){
        viewModelScope.launch {
            useCase.insertNote(list)
            getAllData()
            setState(HomeUiState.SuccessSave)
        }
    }

    fun saveNote(
        title:String,
        desc:String
    ){
        val execute=checkNoteUseCase.executeFields(title,desc)
        val errorMessage=execute.errorMessage?:R.string.notes

        if (!execute.success){
            setState(
                HomeUiState.CheckError(
                    errorMessage
                )
            )

            return
        }
        insertData(listOf(NoteEntity(title,desc)))
    }

     fun searchNote(title: String){
        viewModelScope.launch {
            useCase.searchNote(title).handleResult(
                onComplete = {
                    setState(HomeUiState.SearchSuccess(it))
                },
                onLoading = {

                },
                onError = {
                    setState(HomeUiState.Failure(it.localizedMessage as String))
                }
            )
        }
    }

    fun checkQuery(title:String?){
        if (!title.isNullOrBlank()){
            searchNote(title)
        }else getAllData()
    }
}

sealed class HomeUiState:State{

    data class Success(val list:List<NoteUiModel>):HomeUiState()

    data class Failure(val message:String):HomeUiState()

    data object Loading:HomeUiState()

    data class CheckError(val message:Int):HomeUiState()

    data object SuccessSave:HomeUiState()
    data class SearchSuccess(val list:List<NoteUiModel>):HomeUiState()
}