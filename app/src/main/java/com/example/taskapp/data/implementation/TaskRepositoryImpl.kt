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
    override suspend fun create(task: Task): Long {
        return taskDao.create(taskEntity = task.toTaskEntity())
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(taskEntity = task.toTaskEntity())
    }

    override suspend fun update(task: Task) {
        taskDao.update(taskEntity = task.toTaskEntity())
    }

    override fun getCurrentTasksById(categoryId: Long): Flow<List<Task>> =
        taskDao.getCurrentTasksById(categoryId = categoryId)
            .map { list -> list.map { it.toTask() } }

    override fun getCurrentTaskById(taskId: Long): Flow<Task> =
        taskDao.getCurrentTaskById(taskId = taskId).map { it.toTask() }
}