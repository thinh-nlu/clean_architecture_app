package com.example.notepad.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notepad.ui.theme.Pink40
import com.example.notepad.ui.theme.Pink80
import com.example.notepad.ui.theme.Purple40
import com.example.notepad.ui.theme.Purple80
import com.example.notepad.ui.theme.PurpleGrey40
import com.example.notepad.ui.theme.PurpleGrey80

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(Purple40, PurpleGrey40, Pink40, Pink80, PurpleGrey80, Purple80)
    }

    class InvalidNoteException(message: String) : Exception(message)
}
