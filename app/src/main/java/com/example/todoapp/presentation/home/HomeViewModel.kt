package com.example.todoapp.presentation.home

import com.example.todoapp.R
import androidx.lifecycle.viewModelScope
import com.example.todoapp.common.base.BaseViewModel
import com.example.todoapp.common.base.State
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.data.mapper.toListNoteUiModel
import com.example.todoapp.data.mapper.toLocalDatabaseList
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.domain.useCase.CheckUseCase
import com.example.todoapp.domain.useCase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase:NoteUseCase,
    private val checkUseCase: CheckUseCase
) : BaseViewModel<HomeUiState>() {


    fun submitNote(
        title:String,
        desc:String
    ){
        val executeTitle=checkUseCase.executeTitle(title)
        val executeDesc=checkUseCase.executeDescription(desc)
        val executeList= listOf(executeDesc,executeTitle)
        val hasError=executeList.any{ !it.success}

        if (hasError){
            setState(
                HomeUiState.CheckError(
                    executeList.first { !it.success }.errorMessage?:R.string.error_message_1
                )
            )
            return
        }
        insertData(listOf(NoteUiModel(title,desc)))
    }
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

    private fun insertData(list: List<NoteUiModel>){
        viewModelScope.launch {
            useCase.insertNote(list.toLocalDatabaseList())
            getAllData()
        }
    }
}

sealed class HomeUiState:State{

    data class Success(val list:List<NoteUiModel>):HomeUiState()

    data class Failure(val message:String):HomeUiState()

    data object Loading:HomeUiState()

    data class CheckError(val message:Int):HomeUiState()

}