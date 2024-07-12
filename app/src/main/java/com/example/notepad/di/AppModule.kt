package com.example.notepad.di

import android.app.Application
import androidx.room.Room
import com.example.notepad.feature.data.data_source.NoteDatabase
import com.example.notepad.feature.data.repository.NoteRepositoryImpl
import com.example.notepad.feature.domain.repository.NoteRepository
import com.example.notepad.feature.domain.use_case.AddNoteUseCase
import com.example.notepad.feature.domain.use_case.DeleteNotesUseCase
import com.example.notepad.feature.domain.use_case.GetNoteUseCase
import com.example.notepad.feature.domain.use_case.GetNotesUseCase
import com.example.notepad.feature.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(db.noteDAO)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository) : NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNotesUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}