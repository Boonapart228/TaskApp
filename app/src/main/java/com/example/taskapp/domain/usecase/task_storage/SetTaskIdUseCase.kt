package com.example.taskapp.domain.usecase.task_storage

import com.example.taskapp.domain.TaskIdStorage

class SetTaskIdUseCase(
    private val taskIdStorage: TaskIdStorage
) {
    fun execute(id: Long?) {
        taskIdStorage.setId(id)
    }
}