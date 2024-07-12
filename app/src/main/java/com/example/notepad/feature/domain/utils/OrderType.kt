package com.example.notepad.feature.domain.utils

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}