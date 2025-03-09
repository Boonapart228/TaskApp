package com.example.taskapp.domain.usecase.title_formatter

import com.example.taskapp.domain.TitleFormatter

class GetCorrectTitleUseCase(
    private val titleFormatter: TitleFormatter
) {
    fun execute(title: String): String = titleFormatter.getCorrectTitle(title)
}