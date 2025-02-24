package com.example.taskapp.domain.model.database.views

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT c.id AS categoryId, c.title AS categoryTitle, COUNT(t.id) AS taskCount " +
            "FROM category_table c " +
            "LEFT JOIN task_table t ON c.id = t.categoryId " +
            "GROUP BY c.id, c.title"
)
data class CategoryTaskView(
    val categoryId: Long,
    val categoryTitle: String,
    val taskCount: Int
)
