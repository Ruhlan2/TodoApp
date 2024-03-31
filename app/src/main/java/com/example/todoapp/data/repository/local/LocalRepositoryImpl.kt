package com.example.todoapp.data.repository.local

import com.example.todoapp.common.network.Resource
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.data.mapper.toListNoteUiModel
import com.example.todoapp.data.source.local.LocalDataSource
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val source: LocalDataSource
) :LocalRepository{

    override suspend fun getAll(): Flow<Resource<List<NoteUiModel>>> = flow{
        emit(Resource.Loading)
        when(val response=source.getAll()){
            is Resource.Error->emit(Resource.Error(response.throwable))
            is Resource.Loading->Unit
            is Resource.Success->emit(Resource.Success(response.result.toListNoteUiModel()))
        }
    }

    override suspend fun insertNote(list: List<NoteEntity>) {
        source.insertNote(list)
    }

    override suspend fun searchNote(title: String): Flow<Resource<List<NoteUiModel>>> = flow {
        emit(Resource.Loading)
        when(val response=source.searchNote(title)){
            is Resource.Error->emit(Resource.Error(response.throwable))
            is Resource.Loading->Unit
            is Resource.Success->emit(Resource.Success(response.result.toListNoteUiModel()))
        }
    }

}