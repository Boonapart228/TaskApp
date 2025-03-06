package com.example.taskapp.domain

import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun create(task: Task): Long

    suspend fun update(task: Task)

    suspend fun delete(task: Task)

    fun getCurrentTaskById(taskId: Long): Flow<Task>

    fun getActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>>

    fun getInActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>>

    fun getInActiveRecentTasks(
        noteFilter: String,
        currentCategoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>>

    fun getActiveRecentTasks(
        noteFilter: String,
        currentCategoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>>
}