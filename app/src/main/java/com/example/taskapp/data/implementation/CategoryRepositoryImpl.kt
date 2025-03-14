package com.example.taskapp.data.implementation

import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.model.Category
import com.example.taskapp.domain.model.database.dao.CategoryDao
import com.example.taskapp.domain.model.database.entity.toCategory
import com.example.taskapp.domain.model.database.entity.toCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories().map { list -> list.map { it.toCategory() } }

    override suspend fun createCategory(category: Category) =
        categoryDao.create(categoryEntity = category.toCategoryEntity())

    override suspend fun deleteCategory(category: Category) =
        categoryDao.delete(categoryEntity = category.toCategoryEntity())

    override suspend fun updateCategory(category: Category) =
        categoryDao.update(category.toCategoryEntity())

    override fun getCategoryById(categoryId: Long): Category? =
        categoryDao.getCategoryById(categoryId = categoryId)?.toCategory()

    override fun getCategoryTitleById(categoryId: Long): String? {
        return categoryDao.getCategoryTitleById(categoryId = categoryId)
    }
}