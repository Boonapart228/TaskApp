package com.example.taskapp.domain.usecase.category_storage

import com.example.taskapp.domain.CategoryIdStorage

class GetCategoryIdUseCase(
    private val categoryIdStorage: CategoryIdStorage
) {
   suspend fun execute(): Long? = categoryIdStorage.getId()
}