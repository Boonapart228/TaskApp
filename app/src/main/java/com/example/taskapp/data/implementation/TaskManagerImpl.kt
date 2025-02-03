package com.example.taskapp.data.implementation

import com.example.taskapp.data.local.taskManager
import com.example.taskapp.domain.TaskManager
import com.example.taskapp.domain.model.Task

class TaskManagerImpl : TaskManager {
    override fun addCategories(categories: String) {
        val correctCategoriesName = categories.replace(Regex("\\s+"), " ").trim()
        taskManager.getOrPut(correctCategoriesName) { mutableListOf() }
    }

    override fun getAllTaskManager(): MutableMap<String, MutableList<Task>> {
        return taskManager
    }
}