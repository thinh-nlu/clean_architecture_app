package com.example.notepad.feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepad.feature.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){

    abstract val noteDAO: NoteDAO
    companion object {
        const val DATABASE_NAME = "note_database"
    }
}