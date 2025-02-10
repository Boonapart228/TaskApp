package com.example.taskapp.domain.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.domain.model.database.dao.CategoryDao

@Database(
    version = 1,
    entities = [CategoryEntity::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}