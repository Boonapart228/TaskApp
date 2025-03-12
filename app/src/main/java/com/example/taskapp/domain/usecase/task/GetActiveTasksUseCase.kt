package com.example.taskapp.domain.usecase.task

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

class GetActiveTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> {
        return taskRepository.getActiveTasks(
            categoryId = categoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }
}