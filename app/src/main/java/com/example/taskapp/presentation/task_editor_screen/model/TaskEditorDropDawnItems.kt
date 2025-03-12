package com.example.taskapp.presentation.task_editor_screen.model

import com.example.taskapp.R

enum class TaskEditorDropDawnItems(val iconId: Int, val textId: Int) {
    PIN(iconId = R.drawable.pin_fill_ui, textId = R.string.pin_text),
    UN_PIN(iconId = R.drawable.pin_slash_ui, textId = R.string.unpin_text),
    COLOR(iconId = R.drawable.palette_ui, textId = R.string.color_text),
    DELETE(iconId = R.drawable.trash_ui, textId = R.string.delete_text),
}