package com.example.todoapp.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @ColumnInfo("title_note")
    val title:String,
    @ColumnInfo("note_desc")
    val description:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)