package com.example.taskapp.domain.usecase.category_task

import com.example.taskapp.domain.CategoryTaskRepository
import com.example.taskapp.domain.model.CategoryTaskManager
import kotlinx.coroutines.flow.Flow

class GetCategoryTaskCountsUseCase(
    private val categoryTaskRepository: CategoryTaskRepository
) {
    fun execute(sortBy: String,
                sortDirection: String): Flow<List<CategoryTaskManager>> {
       return categoryTaskRepository.getCategoryTaskCounts(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }
}