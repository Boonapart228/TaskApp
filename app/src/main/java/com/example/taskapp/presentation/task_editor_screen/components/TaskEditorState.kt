package com.example.taskapp.presentation.task_editor_screen.components

data class TaskEditorState(
    val description: String = "",
    val title: String = "",
    val expanded: Boolean = false,
    val pin: Boolean = false
)