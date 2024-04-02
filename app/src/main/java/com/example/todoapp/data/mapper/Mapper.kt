package com.example.todoapp.data.mapper

import androidx.room.Entity
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel

fun NoteEntity?.toNoteUiModel()=NoteUiModel(
    id = this?.id?:-1,
    title = this?.title.orEmpty(),
    description=this?.description.orEmpty()
)

fun List<NoteEntity?>?.toListNoteUiModel()=this?.map {
    it.toNoteUiModel()
}.orEmpty()

fun NoteUiModel.toNoteEntity()=NoteEntity(
    title=title,
    description=description
)

fun List<NoteUiModel>.toNoteEntity() = this.map {
   it.toNoteEntity()
}.orEmpty()