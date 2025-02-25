package com.example.taskapp.data.implementation

import com.example.taskapp.domain.CategoryTaskRepository
import com.example.taskapp.domain.model.CategoryTaskManager
import com.example.taskapp.domain.model.database.dao.CategoryTaskDao
import com.example.taskapp.domain.model.database.views.toCategoryTaskManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryTaskRepositoryImpl(private val categoryTaskDao: CategoryTaskDao) :
    CategoryTaskRepository {
    override fun getCategoryTaskCounts(
        sortBy: String,
        sortDirection: String
    ): Flow<List<CategoryTaskManager>> =
        categoryTaskDao.getCategoryTaskCounts(
            sortBy = sortBy,
            sortDirection = sortDirection
        )
            .map { list -> list.map { it.toCategoryTaskManager() } }
}