package com.example.taskapp.domain

import com.example.taskapp.domain.model.CategoryTaskManager
import kotlinx.coroutines.flow.Flow

interface CategoryTaskRepository {
    fun getCategoryTaskCounts(
        sortBy: String,
        sortDirection: String
    ): Flow<List<CategoryTaskManager>>
}