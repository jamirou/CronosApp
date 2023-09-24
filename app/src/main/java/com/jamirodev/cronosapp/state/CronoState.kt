package com.jamirodev.cronosapp.state

data class CronoState(
    val chronometerActive: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String = ""
)
