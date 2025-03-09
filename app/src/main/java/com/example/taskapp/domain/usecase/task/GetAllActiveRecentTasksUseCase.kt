package com.example.taskapp.domain.usecase.task

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

class GetAllActiveRecentTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> {
        return taskRepository.getAllActiveRecentTasks(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
    }
}