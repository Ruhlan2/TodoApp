package com.example.todoapp.data.service.local

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.todoapp.data.dto.local.NoteEntity


@Dao
interface NotesDAO {

    @Query("SELECT * FROM noteentity")
    suspend fun getAll():List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(data:NoteEntity)


    @Query("DELETE FROM noteentity WHERE id=:noteID")
    suspend fun deleteNote(noteID:Int)


    @Query("SELECT * FROM noteentity WHERE noteentity.title_note LIKE :title")
    suspend fun searchNote(title:String):List<NoteEntity>


}