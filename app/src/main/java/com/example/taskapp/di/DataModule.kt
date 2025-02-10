package com.example.taskapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.implementation.CategoryRepositoryImpl
import com.example.taskapp.data.implementation.TaskManagerImpl
import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.TaskManager
import com.example.taskapp.domain.model.database.AppDataBase
import com.example.taskapp.domain.model.database.dao.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext applicationContext: Context): AppDataBase {
        return Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "database.db"
        ).build()
    }

    @Provides
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(categoryDao)
    }

    @Provides
    @Singleton
    fun provideUserDao(appDataBase: AppDataBase): CategoryDao {
        return appDataBase.getCategoryDao()
    }

    @Provides
    fun provideTaskManager(): TaskManager {
        return TaskManagerImpl()
    }
}