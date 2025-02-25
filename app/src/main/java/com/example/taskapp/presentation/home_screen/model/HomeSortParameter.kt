package com.example.taskapp.presentation.home_screen.model

import com.example.taskapp.R

enum class HomeSortParameter(val parameter: String, val textId: Int, val iconId: Int) {
    TITLE("title", textId = R.string.sort_by_title_text, iconId = R.drawable.sort_by_title_ui),
    DATE("date", textId = R.string.sort_by_date_text, iconId = R.drawable.sort_by_date_ui)
}