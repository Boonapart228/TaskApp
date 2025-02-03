package com.example.taskapp.di

import com.example.taskapp.data.implementation.TaskManagerImpl
import com.example.taskapp.domain.TaskManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideTaskManager(): TaskManager {
        return TaskManagerImpl()
    }
}