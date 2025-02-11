package com.example.taskapp.data.implementation

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.model.database.dao.TaskDao
import com.example.taskapp.domain.model.database.toTask
import com.example.taskapp.domain.model.database.toTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun create(task: Task) {
        taskDao.create(taskEntity = task.toTaskEntity())
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(taskEntity = task.toTaskEntity())
    }

    override suspend fun update(task: Task) {
        taskDao.update(taskEntity = task.toTaskEntity())
    }

    override fun getCurrentTaskById(categoryId: Long): Flow<List<Task>> =
        taskDao.getCurrentTaskById(categoryId = categoryId)
            .map { list -> list.map { it.toTask() } }

}