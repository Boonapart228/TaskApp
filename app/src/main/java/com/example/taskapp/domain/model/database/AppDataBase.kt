package com.example.taskapp.domain.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.domain.model.database.dao.CategoryDao
import com.example.taskapp.domain.model.database.dao.TaskDao

@Database(
    version = 1,
    entities = [CategoryEntity::class, TaskEntity::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getTaskDao(): TaskDao
}