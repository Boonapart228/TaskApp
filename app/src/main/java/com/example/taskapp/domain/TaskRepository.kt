package com.example.taskapp.domain

import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun create(task : Task)

    suspend fun update(task : Task)

    suspend fun delete(task : Task)

    fun getCurrentTaskById(categoryId: Long): Flow<List<Task>>

}