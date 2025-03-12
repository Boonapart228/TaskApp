package com.example.taskapp.domain.usecase.date_formatter

import com.example.taskapp.domain.DateTimeFormatter

class FormatDateUseCase(
    private val dateTimeFormatter: DateTimeFormatter
) {
    fun execute(timestamp: Long) = dateTimeFormatter.formatDate(timestamp)
}