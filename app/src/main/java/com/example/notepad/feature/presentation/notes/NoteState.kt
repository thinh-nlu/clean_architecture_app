package com.example.notepad.feature.presentation.notes

import com.example.notepad.feature.domain.model.Note
import com.example.notepad.feature.domain.utils.NoteOrder
import com.example.notepad.feature.domain.utils.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
