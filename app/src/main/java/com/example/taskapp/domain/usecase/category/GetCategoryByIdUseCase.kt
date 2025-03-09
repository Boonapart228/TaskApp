package com.example.taskapp.domain.usecase.category

import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.model.Category

class GetCategoryByIdUseCase(
    private val categoryRepository: CategoryRepository
) {
    fun execute(categoryId: Long): Category? {
        return categoryRepository.getCategoryById(categoryId)
    }
}