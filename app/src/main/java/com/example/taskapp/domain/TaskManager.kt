package com.example.taskapp.domain

import com.example.taskapp.domain.model.Task

interface TaskManager {
    fun addCategories(categories: String)
    fun getAllTaskManager(): MutableMap<String, MutableList<Task>>
}