package com.example.todoapp.domain.useCase

import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel
import com.example.todoapp.domain.repository.LocalRepository
import javax.inject.Inject


class NoteUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend fun getAll()=repository.getAll()

    suspend fun insertNote(list:NoteEntity)=repository.insertNote(list)

    suspend fun searchNote(title:String)=repository.searchNote(title)

    suspend fun deleteNote(id:Int)=repository.deleteNote(id)

}