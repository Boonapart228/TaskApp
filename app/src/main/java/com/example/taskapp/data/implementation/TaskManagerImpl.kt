package com.example.taskapp.data.implementation

import com.example.taskapp.data.local.taskManager
import com.example.taskapp.domain.TaskManager
import com.example.taskapp.domain.model.Task

class TaskManagerImpl : TaskManager {
    override fun addCategory(category: String) {
        val correctCategoriesName = category.replace(Regex("\\s+"), " ").trim()
        taskManager.getOrPut(correctCategoriesName) { mutableListOf() }
    }

    override fun getAllTaskManager(): MutableMap<String, MutableList<Task>> {
        return taskManager
    }

    override fun deleteCategory(category: String) {
        val correctCategoriesName = category.replace(Regex("\\s+"), " ").trim()
        if (checkCategory(correctCategoriesName)) {
            taskManager.remove(correctCategoriesName)
        }
    }

    override fun updateCategory(oldCategoryTitle: String, newCategoryTitle: String) {
        val correctNewCategoriesName = newCategoryTitle.replace(Regex("\\s+"), " ").trim()
        val correctOldCategoriesName = oldCategoryTitle.replace(Regex("\\s+"), " ").trim()
        if (!checkCategory(correctOldCategoriesName)) {
            return
        }
        if (checkCategory(correctNewCategoriesName)) {
            return
        }
        val values = taskManager.remove(correctOldCategoriesName) ?: mutableListOf()
        taskManager[correctNewCategoriesName] = values
    }


    private fun checkCategory(category: String): Boolean {
        return taskManager.containsKey(category)
    }
}