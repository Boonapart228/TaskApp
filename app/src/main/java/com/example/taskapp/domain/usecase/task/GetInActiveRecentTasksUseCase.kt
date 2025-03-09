package com.example.taskapp.domain.usecase.task

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

class GetInActiveRecentTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(
        noteFilter: String,
        currentCategoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> {
        return taskRepository.getInActiveRecentTasks(
            noteFilter = noteFilter,
            currentCategoryId = currentCategoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }
}