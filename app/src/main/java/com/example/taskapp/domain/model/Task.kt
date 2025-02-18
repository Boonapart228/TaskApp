package com.example.taskapp.domain.model

data class Task(
    val id: Long,
    val categoryId: Long,
    val title: String,
    var description: String,
    var isActive: Boolean,
    val hexColorCode: String,
    val createdAt: Long = System.currentTimeMillis()
)