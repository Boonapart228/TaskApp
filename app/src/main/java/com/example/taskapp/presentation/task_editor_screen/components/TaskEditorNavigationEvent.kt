package com.example.taskapp.presentation.task_editor_screen.components

sealed class TaskEditorNavigationEvent {
    data object NavigationToHome : TaskEditorNavigationEvent()
}