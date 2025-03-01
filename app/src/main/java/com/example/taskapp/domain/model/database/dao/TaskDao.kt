package com.example.taskapp.domain.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskapp.domain.model.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun create(taskEntity: TaskEntity): Long

    @Update
    suspend fun update(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    fun getCurrentTaskById(taskId: Long): Flow<TaskEntity>


    @Query(
        "SELECT * FROM task_table " +
                "WHERE categoryId = :categoryId AND isActive = 1 " +
                "ORDER BY " +
                "CASE WHEN :sortDirection = 'ASC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' Then createdAt " +
                "ELSE NULL END) " +
                "END ASC, " +
                "CASE WHEN :sortDirection = 'DESC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' Then createdAt " +
                "ELSE NULL END) " +
                "END DESC"
    )
    fun getActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<TaskEntity>>


    @Query(
        "SELECT * FROM task_table " +
                "WHERE categoryId = :categoryId AND isActive = 0 " +
                "ORDER BY " +
                "CASE WHEN :sortDirection = 'ASC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' Then createdAt " +
                "ELSE NULL END) " +
                "END ASC, " +
                "CASE WHEN :sortDirection = 'DESC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' Then createdAt " +
                "ELSE NULL END) " +
                "END DESC"
    )
    fun getInActiveTasks(
        categoryId: Long,
        sortBy: String,
        sortDirection: String
    ): Flow<List<TaskEntity>>

    @Query(
        "SELECT * FROM (" +
                "SELECT * FROM task_table " +
                " WHERE isActive = 0 " +
                "ORDER BY lastActiveAt DESC " +
                "LIMIT 10 " +
                ") AS limited_tasks " +
                "ORDER BY " +
                "CASE WHEN :sortDirection = 'ASC' THEN " +
                "CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' THEN createdAt " +
                "ELSE NULL END " +
                "END ASC, " +
                "CASE WHEN :sortDirection = 'DESC' THEN " +
                "CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' THEN createdAt " +
                "ELSE NULL END " +
                "END DESC"
    )
    fun getInActiveRecentTasks(
        sortBy: String,
        sortDirection: String
    ): Flow<List<TaskEntity>>


    @Query(
        "SELECT * FROM (" +
                "SELECT * FROM task_table " +
                " WHERE isActive = 1 " +
                "ORDER BY lastActiveAt DESC " +
                "LIMIT 10 " +
                ") AS limited_tasks " +
                "ORDER BY " +
                "CASE WHEN :sortDirection = 'ASC' THEN " +
                "CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' THEN createdAt " +
                "ELSE NULL END " +
                "END ASC, " +
                "CASE WHEN :sortDirection = 'DESC' THEN " +
                "CASE :sortBy " +
                "WHEN 'title' THEN LOWER(title) " +
                "WHEN 'date' THEN createdAt " +
                "ELSE NULL END " +
                "END DESC"
    )
    fun getActiveRecentTasks(
        sortBy: String,
        sortDirection: String
    ): Flow<List<TaskEntity>>
}