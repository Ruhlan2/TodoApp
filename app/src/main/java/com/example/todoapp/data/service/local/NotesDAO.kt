package com.example.todoapp.data.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.data.dto.local.NoteEntity
import com.example.todoapp.domain.model.NoteUiModel


@Dao
interface NotesDAO {

    @Query("SELECT * FROM noteentity")
    suspend fun getAll():List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(data:List<NoteEntity>)

    @Query("DELETE  FROM noteentity WHERE id=:noteID")
    suspend fun deleteNote(noteID:Int)


}