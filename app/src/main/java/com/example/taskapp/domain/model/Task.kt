package com.example.taskapp.domain.model

data class Task(
    val title: String,
    var description: String,
    var isActive: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
)