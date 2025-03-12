package com.example.taskapp.presentation.setting_screen.models

import com.example.taskapp.R

enum class RecentNoteFilter(val type: String, val textId: Int) {
    CURRENT(type = "current", textId = R.string.current_note_filter),
    ALL(type = "all", textId = R.string.all_note_filter)
}