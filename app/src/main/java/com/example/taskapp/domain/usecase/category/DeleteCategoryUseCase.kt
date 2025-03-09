package com.example.taskapp.domain.usecase.category

import com.example.taskapp.domain.CategoryRepository
import com.example.taskapp.domain.model.Category

class DeleteCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun execute(category : Category){
        categoryRepository.deleteCategory(category)
    }
}