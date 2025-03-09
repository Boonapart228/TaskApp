package com.example.taskapp.domain.usecase.category_storage

import com.example.taskapp.domain.CategoryIdStorage

class SetCategoryIdUseCase(
    private val categoryIdStorage: CategoryIdStorage
) {
    suspend fun execute(id: Long?) {
        categoryIdStorage.setId(id)
    }
}