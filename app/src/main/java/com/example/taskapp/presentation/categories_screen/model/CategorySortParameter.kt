package com.example.taskapp.presentation.categories_screen.model

import com.example.taskapp.R

enum class CategorySortParameter(val parameter: String, val textId: Int, val iconId: Int) {
    TITLE("title", textId = R.string.sort_by_title_text, iconId = R.drawable.sort_by_title_ui),
    NOTE("note", textId = R.string.sort_by_note_text, iconId = R.drawable.sort_by_note)
}