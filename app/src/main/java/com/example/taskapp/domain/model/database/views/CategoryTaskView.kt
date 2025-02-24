package com.example.taskapp.domain.model.database.views

import androidx.room.DatabaseView
import com.example.taskapp.domain.model.CategoryTaskManager

@DatabaseView(
    viewName = "category_task_view",
    value = "SELECT c.id AS categoryId, c.title AS categoryTitle, c.hexColorCode AS categoryHexColorCode,COUNT(t.id) AS taskCount " +
            "FROM category_table c " +
            "LEFT JOIN task_table t ON c.id = t.categoryId " +
            "GROUP BY c.id, c.title"
)
data class CategoryTaskView(
    val categoryId: Long,
    val categoryTitle: String,
    val categoryHexColorCode: String,
    val taskCount: Int
)

fun CategoryTaskView.toCategoryTaskManager() = CategoryTaskManager(
    categoryId = categoryId,
    categoryTitle = categoryTitle,
    categoryHexColorCode = categoryHexColorCode,
    taskCount = taskCount
)