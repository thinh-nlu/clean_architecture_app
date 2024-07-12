package com.example.notepad.feature.domain.use_case

import com.example.notepad.feature.domain.model.Note
import com.example.notepad.feature.domain.repository.NoteRepository

class DeleteNotesUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}