package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.common.utils.Constants
import com.example.todoapp.data.repository.local.LocalRepositoryImpl
import com.example.todoapp.data.service.local.LocalService
import com.example.todoapp.data.service.local.NotesDAO
import com.example.todoapp.data.source.local.LocalDataSource
import com.example.todoapp.data.source.local.LocalDataSourceImpl
import com.example.todoapp.domain.repository.LocalRepository
import com.example.todoapp.domain.useCase.CheckNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(
            context,
            LocalService::class.java,
            Constants.DATABASE
        ).build()

    @Singleton
    @Provides
    fun injectRoomDao(localService: LocalService)=localService.getDao()

    @Singleton
    @Provides
    fun injectLocalSource(dao:NotesDAO)=LocalDataSourceImpl(dao) as LocalDataSource

    @Singleton
    @Provides
    fun injectLocalRepository(source: LocalDataSource)=LocalRepositoryImpl(source) as LocalRepository


    @Singleton
    @Provides
    fun injectCheckUseCase()=CheckNoteUseCase()
}