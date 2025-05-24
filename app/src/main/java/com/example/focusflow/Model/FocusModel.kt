package com.example.focusflow.Model

data class FocusModel(
    val focus_id: Int = 0,
    val title: String = "",
    val category: String = "",
    val goals: String = "",
    val focusDuration: Int = 0,
    val restDuration: Int = 0,
    val isCompleted: Boolean = false
)