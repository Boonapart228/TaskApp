package com.example.taskapp.domain.model.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.taskapp.domain.model.Task

@Entity(
    tableName = "task_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val categoryId: Long,
    val title: String,
    var description: String,
    var isActive: Boolean,
    val hexColorCode: String,
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveAt: Long
)

fun TaskEntity.toTask() = Task(
    id = id,
    categoryId = categoryId,
    title = title,
    description = description,
    isActive = isActive,
    hexColorCode = hexColorCode,
    createdAt = createdAt,
    lastActiveAt = lastActiveAt
)

fun Task.toTaskEntity() = TaskEntity(
    id = id,
    categoryId = categoryId,
    title = title,
    description = description,
    isActive = isActive,
    hexColorCode = hexColorCode,
    createdAt = createdAt,
    lastActiveAt = lastActiveAt
)