package com.example.taskapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.implementation.AppSettingsImpl
import com.example.taskapp.data.implementation.CategoryIdStorageImpl
import com.example.taskapp.data.implementation.CategoryRepositoryImpl
import com.example.taskapp.data.implementation.DateTimeFormatterImpl
import com.example.taskapp.data.implementation.TaskRepositoryImpl
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.DateTimeFormatter
import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.model.database.AppDataBase
import com.example.taskapp.domain.model.database.dao.CategoryDao
import com.example.taskapp.domain.model.database.dao.TaskDao
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
    fun provideCategoryDao(appDataBase: AppDataBase): CategoryDao {
        return appDataBase.getCategoryDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDataBase: AppDataBase): TaskDao {
        return appDataBase.getTaskDao()
    }

    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }

    @Provides
    @Singleton
    fun provideCategoryIdStorage(): CategoryIdStorage {
        return CategoryIdStorageImpl()
    }

    @Provides
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatterImpl()
    }

    @Provides
    @Singleton
    fun provideAppSettings(@ApplicationContext applicationContext: Context): AppSettings {
        return AppSettingsImpl(applicationContext)
    }
}