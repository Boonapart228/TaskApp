package com.example.taskapp.domain.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.domain.model.database.dao.CategoryDao
import com.example.taskapp.domain.model.database.dao.TaskDao
import com.example.taskapp.domain.model.database.entity.CategoryEntity
import com.example.taskapp.domain.model.database.entity.TaskEntity
import com.example.taskapp.domain.model.database.views.CategoryTaskView

@Database(
    version = 1,
    entities = [CategoryEntity::class, TaskEntity::class],
    views = [CategoryTaskView::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getTaskDao(): TaskDao
}