package com.example.taskapp.data.implementation

import com.example.taskapp.domain.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTimeFormatterImpl : DateTimeFormatter {
    override fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    override fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}