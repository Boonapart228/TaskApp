package com.example.taskapp.domain.model

data class CategoryTaskManager(
    val categoryId: Long,
    val categoryTitle: String,
    val categoryHexColorCode: String,
    val taskCount: Int
)
