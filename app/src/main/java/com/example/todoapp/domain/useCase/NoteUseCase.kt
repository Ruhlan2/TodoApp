package com.example.todoapp.domain.useCase

import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.repository.LocalRepository
import javax.inject.Inject


class NoteUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend fun getAll()=repository.getAll()

    suspend fun insertNote(list:List<NoteEntity>)=repository.insertNote(list)


}