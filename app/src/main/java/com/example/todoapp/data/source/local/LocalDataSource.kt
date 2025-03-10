package com.example.todoapp.data.source.local

import com.example.todoapp.common.network.Resource
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel

interface LocalDataSource {

    suspend fun getAll():Resource<List<NoteEntity>>

    suspend fun insertNote(list: List<NoteEntity>)
}