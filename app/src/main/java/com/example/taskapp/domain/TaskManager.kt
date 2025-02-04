package com.example.taskapp.domain

import com.example.taskapp.domain.model.Task

interface TaskManager {
    fun addCategory(category: String)
    fun getAllTaskManager(): MutableMap<String, MutableList<Task>>
    fun deleteCategory(category: String)
    fun updateCategory(oldCategoryTitle: String, newCategoryTitle: String)
}