package com.example.todoapp.domain.repository

import com.example.todoapp.common.network.Resource
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel
import java.util.concurrent.Flow

interface LocalRepository {

    suspend fun getAll():kotlinx.coroutines.flow.Flow<Resource<List<NoteUiModel>>>

    suspend fun insertNote(list: List<NoteEntity>)
}