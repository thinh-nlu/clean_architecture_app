package com.example.notepad.feature.domain.use_case

data class NoteUseCase(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNotesUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)