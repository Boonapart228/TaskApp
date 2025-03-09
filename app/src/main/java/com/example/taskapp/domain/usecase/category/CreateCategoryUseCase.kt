package com.example.taskapp.domain.usecase.category

import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.model.Category

class CreateCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun execute(category: Category) {
        categoryRepository.createCategory(category)
    }
}