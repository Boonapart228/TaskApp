package com.example.taskapp.presentation.home_screen.model

import com.example.taskapp.R

enum class NotesFilterType(val textId: Int) {
    ALL(textId = R.string.all_notes),
    RECENT(textId = R.string.recent)
}