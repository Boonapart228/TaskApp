package com.example.taskapp.domain.usecase.date_formatter

import com.example.taskapp.domain.DateTimeFormatter

class FormatTimeUseCase(
    private val dateTimeFormatter: DateTimeFormatter
) {
    fun execute(timestamp: Long): String = dateTimeFormatter.formatTime(timestamp)
}