package com.example.taskapp.domain.usecase.task_actions

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun execute(task: Task) {
        taskRepository.delete(task)
    }
}