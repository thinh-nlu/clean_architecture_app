package com.example.notepad.feature.domain.use_case

import com.example.notepad.feature.domain.model.Note
import com.example.notepad.feature.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(Note.InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw Note.InvalidNoteException("The title of note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw Note.InvalidNoteException("The content of note can't be empty")
        }
        repository.insertNote(note)
    }
}