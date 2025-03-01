package com.example.taskapp.presentation.home_screen.model

import com.example.taskapp.R

enum class NotesFilterType(val textId: Int, val filterType: String) {
    ALL(textId = R.string.all_notes, filterType = "ALL"),
    RECENT(textId = R.string.recent, filterType = "RECENT")
}