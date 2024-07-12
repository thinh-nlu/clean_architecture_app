package com.example.notepad.feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.feature.domain.model.Note
import com.example.notepad.feature.domain.use_case.NoteUseCase
import com.example.notepad.feature.domain.utils.NoteOrder
import com.example.notepad.feature.domain.utils.OrderType
import com.example.notepad.feature.presentation.notes.NoteEvent
import com.example.notepad.feature.presentation.notes.NoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _noteState = mutableStateOf(NoteState())
    val noteState: State<NoteState> = _noteState
    private var getNoteJob: Job? = null
    private var recenlyDeteleNote: Note? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNoteJob?.cancel()
        getNoteJob = noteUseCase.getNotes(noteOrder)
            .onEach {
                notes ->
                _noteState.value = noteState.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (noteState.value::class == event.noteOrder::class
                    && noteState.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    recenlyDeteleNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(recenlyDeteleNote ?: return@launch)
                    recenlyDeteleNote = null
                }
            }

            is NoteEvent.ToggleOrderSection -> {
                _noteState.value = noteState.value.copy(
                    isOrderSectionVisible = !noteState.value.isOrderSectionVisible
                )
            }
        }
    }
}