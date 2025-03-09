package com.example.taskapp.domain.usecase.task

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

class GetInActiveTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> {
        return taskRepository.getInActiveTasks(
            categoryId = categoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }
}