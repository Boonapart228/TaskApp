package com.example.taskapp.presentation.setting_screen.models

import com.example.taskapp.R

enum class Language(
    val languageCode: String,
    val languageTextId: Int,
) {
    ENGLISH(languageCode = "en", languageTextId = R.string.english_language_text),
    UKRAINE(languageCode = "uk", languageTextId = R.string.ukrainian_language_text)
}