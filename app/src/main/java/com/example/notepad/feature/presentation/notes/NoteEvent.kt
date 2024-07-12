package com.example.notepad.feature.presentation.notes

import com.example.notepad.feature.domain.model.Note
import com.example.notepad.feature.domain.utils.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    data object RestoreNote : NoteEvent()
    data object ToggleOrderSection : NoteEvent()
}