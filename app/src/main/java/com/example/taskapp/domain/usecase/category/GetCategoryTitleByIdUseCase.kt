package com.example.taskapp.domain.usecase.category

import com.example.taskapp.domain.CategoryRepository

class GetCategoryTitleByIdUseCase(
    private val categoryRepository: CategoryRepository
) {
    fun execute(categoryId: Long): String? = categoryRepository.getCategoryTitleById(categoryId)
}