package com.example.taskapp.data.implementation

import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.model.database.dao.TaskDao
import com.example.taskapp.domain.model.database.entity.toTask
import com.example.taskapp.domain.model.database.entity.toTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
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

    override fun getCurrentTaskById(taskId: Long): Flow<Task> =
        taskDao.getCurrentTaskById(taskId = taskId)
            .filterNotNull()
            .map { it.toTask() }

    override fun getActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> =
        taskDao.getActiveTasks(
            categoryId = categoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        ).map { list -> list.map { it.toTask() } }

    override fun getInActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> =
        taskDao.getInActiveTasks(
            categoryId = categoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        ).map { list -> list.map { it.toTask() } }

    override fun getInActiveRecentTasks(
        noteFilter: String,
        currentCategoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> =
        taskDao.getInActiveRecentTasks(
            noteFilter = noteFilter,
            currentCategoryId = currentCategoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        ).map { list -> list.map { it.toTask() } }

    override fun getActiveRecentTasks(
        noteFilter: String,
        currentCategoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<Task>> =
        taskDao.getActiveRecentTasks(
            noteFilter = noteFilter,
            currentCategoryId = currentCategoryId,
            sortBy = sortBy,
            sortDirection = sortDirection
        ).map { list -> list.map { it.toTask() } }

}