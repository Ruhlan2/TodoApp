package com.example.todoapp.data.mapper

import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel

fun NoteEntity?.toNoteUiModel()=NoteUiModel(
    title = this?.title.orEmpty(),
    description=this?.description.orEmpty()
)

fun List<NoteEntity?>?.toListNoteUiModel()=this?.map {
    it.toNoteUiModel()
}.orEmpty()

fun NoteUiModel.toNoteDTO(noteId:Int)=NoteEntity(
    title = title,
    description=description,
    id = noteId
)
fun List<NoteUiModel>.toLocalDatabaseList()=mapIndexed { index, noteUiModel ->
    noteUiModel.toNoteDTO(index)
}