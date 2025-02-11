package com.example.taskapp.domain.model.database

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
            onDelete = ForeignKey.CASCADE // Якщо видалимо категорію, її завдання теж видаляться
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
    val createdAt: Long = System.currentTimeMillis()
)

fun TaskEntity.toTask() = Task(
    id = id,
    categoryId = categoryId,
    title = title,
    description = description,
    isActive = isActive,
    createdAt = createdAt
)

fun Task.toTaskEntity() = TaskEntity(
    id = id,
    categoryId = categoryId,
    title = title,
    description = description,
    isActive = isActive,
    createdAt = createdAt
)