package com.example.taskapp.presentation.task_editor_screen.components

data class TaskEditorState(
    val description: String = "",
    val title: String = "",
    val oldTitle: String = "",
    val oldDescription: String = "",
    val hexColorCode: String = "",
    val previewColorCode: String = "",
    val oldHexColorCode: String = "",
    val expanded: Boolean = false,
    val pin: Boolean = false,
    val oldPin: Boolean = false,
    val fieldsChanged: Boolean = false,
    val showDialogColorPicker: Boolean = false,
)