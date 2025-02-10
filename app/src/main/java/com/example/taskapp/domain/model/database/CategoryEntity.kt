package com.example.taskapp.domain.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskapp.domain.model.Category

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String = ""
)

fun CategoryEntity.toCategory() = Category(
    id = id,
    title = title
)

fun Category.toCategoryEntity() = CategoryEntity(
    id = id,
    title = title
)