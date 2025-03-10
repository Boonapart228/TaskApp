package com.example.taskapp.domain.usecase.task

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

class GetCurrentTaskByIdUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(taskId: Long): Flow<Task>
    {
       return taskRepository.getCurrentTaskById(taskId)
    }
}