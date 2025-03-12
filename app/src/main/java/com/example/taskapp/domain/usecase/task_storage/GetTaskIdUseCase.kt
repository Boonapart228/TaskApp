package com.example.taskapp.domain.usecase.task_storage

import com.example.taskapp.domain.TaskIdStorage

class GetTaskIdUseCase(
    private val taskIdStorage: TaskIdStorage
) {
    fun execute(): Long? {
        return taskIdStorage.getId()
    }
}