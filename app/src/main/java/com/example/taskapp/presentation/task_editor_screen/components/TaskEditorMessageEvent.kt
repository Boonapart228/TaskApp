package com.example.taskapp.presentation.task_editor_screen.components

sealed class TaskEditorMessageEvent {
    data object TaskCreationSuccess : TaskEditorMessageEvent()
    data object TaskUpdateSuccess : TaskEditorMessageEvent()
    data object CannotDeleteNonExistentTask : TaskEditorMessageEvent()
    data object TaskDeletionSuccess : TaskEditorMessageEvent()
    data object TaskTitleCannotBeEmpty : TaskEditorMessageEvent()
}