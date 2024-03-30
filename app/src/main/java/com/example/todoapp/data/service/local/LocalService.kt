package com.example.todoapp.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.dto.local.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class LocalService:RoomDatabase(){

    abstract fun getDao():NotesDAO
}