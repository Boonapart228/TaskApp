package com.example.taskapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.implementation.AppSettingsImpl
import com.example.taskapp.data.implementation.CategoryIdStorageImpl
import com.example.taskapp.data.implementation.CategoryRepositoryImpl
import com.example.taskapp.data.implementation.CategoryTaskRepositoryImpl
import com.example.taskapp.data.implementation.DateTimeFormatterImpl
import com.example.taskapp.data.implementation.TaskIdStorageImpl
import com.example.taskapp.data.implementation.TaskRepositoryImpl
import com.example.taskapp.data.implementation.TitleFormatterImpl
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.domain.CategoryIdStorage
import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.CategoryTaskRepository
import com.example.taskapp.domain.DateTimeFormatter
import com.example.taskapp.domain.TaskIdStorage
import com.example.taskapp.domain.TaskRepository
import com.example.taskapp.domain.TitleFormatter
import com.example.taskapp.domain.model.database.AppDataBase
import com.example.taskapp.domain.model.database.dao.CategoryDao
import com.example.taskapp.domain.model.database.dao.CategoryTaskDao
import com.example.taskapp.domain.model.database.dao.TaskDao
import com.example.taskapp.domain.usecase.category_storage.GetCategoryIdUseCase
import com.example.taskapp.domain.usecase.date_formatter.FormatDateUseCase
import com.example.taskapp.domain.usecase.date_formatter.FormatTimeUseCase
import com.example.taskapp.domain.usecase.settings.GetGridColumnsUseCase
import com.example.taskapp.domain.usecase.settings.GetRecentNoteFilterUseCase
import com.example.taskapp.domain.usecase.settings.SetGridColumnsUseCase
import com.example.taskapp.domain.usecase.task.GetActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetActiveTasksUseCase
import com.example.taskapp.domain.usecase.task.GetAllActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetAllInActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetInActiveRecentTasksUseCase
import com.example.taskapp.domain.usecase.task.GetInActiveTasksUseCase
import com.example.taskapp.domain.usecase.task_storage.SetTaskIdUseCase
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
    fun provideCategoryIdStorage(@ApplicationContext applicationContext: Context): CategoryIdStorage {
        return CategoryIdStorageImpl(applicationContext)
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

    @Provides
    @Singleton
    fun provideTaskIdStorage(): TaskIdStorage {
        return TaskIdStorageImpl()
    }

    @Provides
    @Singleton
    fun provideCategoryTaskDao(appDataBase: AppDataBase): CategoryTaskDao {
        return appDataBase.getCategoryTaskDao()
    }

    @Provides
    fun provideCategoryTaskRepository(categoryTaskDao: CategoryTaskDao): CategoryTaskRepository {
        return CategoryTaskRepositoryImpl(categoryTaskDao)
    }

    @Provides
    fun provideTitleFormatter(): TitleFormatter {
        return TitleFormatterImpl()
    }

    @Provides
    fun provideGetCategoryIdUseCase(categoryIdStorage: CategoryIdStorage): GetCategoryIdUseCase {
        return GetCategoryIdUseCase(categoryIdStorage)
    }

    @Provides
    fun provideFormatDateUseCase(dateTimeFormatter: DateTimeFormatter): FormatDateUseCase {
        return FormatDateUseCase(dateTimeFormatter)
    }

    @Provides
    fun provideFormatTimeUseCase(dateTimeFormatter: DateTimeFormatter): FormatTimeUseCase {
        return FormatTimeUseCase(dateTimeFormatter)
    }

    @Provides
    fun provideSetTaskIdUseCase(taskIdStorage: TaskIdStorage): SetTaskIdUseCase {
        return SetTaskIdUseCase(taskIdStorage)
    }

    @Provides
    fun provideSetGridColumnsUseCase(appSettings: AppSettings): SetGridColumnsUseCase {
        return SetGridColumnsUseCase(appSettings)
    }

    @Provides
    fun provideGetGridColumnsUseCase(appSettings: AppSettings): GetGridColumnsUseCase {
        return GetGridColumnsUseCase(appSettings)
    }

    @Provides
    fun provideGetRecentNoteFilterUseCase(appSettings: AppSettings): GetRecentNoteFilterUseCase {
        return GetRecentNoteFilterUseCase(appSettings)
    }

    @Provides
    fun provideGetActiveTasksUseCase(taskRepository: TaskRepository): GetActiveTasksUseCase {
        return GetActiveTasksUseCase(taskRepository)
    }

    @Provides
    fun provideGetInActiveTasksUseCase(taskRepository: TaskRepository): GetInActiveTasksUseCase {
        return GetInActiveTasksUseCase(taskRepository)
    }

    @Provides
    fun provideGetActiveRecentTasksUseCase(taskRepository: TaskRepository): GetActiveRecentTasksUseCase {
        return GetActiveRecentTasksUseCase(taskRepository)
    }

    @Provides
    fun provideGetInActiveRecentTasksUseCase(taskRepository: TaskRepository): GetInActiveRecentTasksUseCase {
        return GetInActiveRecentTasksUseCase(taskRepository)
    }

    @Provides
    fun provideGetAllActiveRecentTasksUseCase(taskRepository: TaskRepository): GetAllActiveRecentTasksUseCase {
        return GetAllActiveRecentTasksUseCase(taskRepository)
    }

    @Provides
    fun provideGetAllInActiveRecentTasksUseCase(taskRepository: TaskRepository): GetAllInActiveRecentTasksUseCase {
        return GetAllInActiveRecentTasksUseCase(taskRepository)
    }
}