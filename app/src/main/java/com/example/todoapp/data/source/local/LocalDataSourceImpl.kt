package com.example.todoapp.data.source.local

import com.example.todoapp.common.network.Resource
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.data.mapper.toListNoteUiModel
import com.example.todoapp.data.service.local.NotesDAO
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val service:NotesDAO
) :LocalDataSource{

    override suspend fun getAll(): Resource<List<NoteEntity>> =handleResponse { service.getAll() }
    override suspend fun insertNote(list: List<NoteEntity>) {
        service.insertNote(list)
    }


    private suspend fun <T> handleResponse(response : suspend()->T):Resource<T>{
        return try {
            val data=response.invoke()
            Resource.Success(data)
        }catch (e:Exception){
            Resource.Error(e)
        }
    }
}