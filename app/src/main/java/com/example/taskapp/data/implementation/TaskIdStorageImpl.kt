package com.example.taskapp.data.implementation

import com.example.taskapp.domain.TaskIdStorage

class TaskIdStorageImpl : TaskIdStorage {
    private var taskId: Long? = null
    override fun setId(id: Long?) {
        taskId = id
    }

    override fun getId(): Long? {
        return taskId
    }
}