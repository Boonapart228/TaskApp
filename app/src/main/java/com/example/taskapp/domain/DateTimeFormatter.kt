package com.example.taskapp.domain

interface DateTimeFormatter {
    fun formatDate(timestamp: Long): String
    fun formatTime(timestamp: Long): String
}