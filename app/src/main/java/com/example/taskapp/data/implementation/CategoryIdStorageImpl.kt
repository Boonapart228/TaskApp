package com.example.taskapp.data.implementation

import com.example.taskapp.domain.CategoryIdStorage

class CategoryIdStorageImpl : CategoryIdStorage {
    private var categoryId: Long? = null

    override fun getId(): Long? = categoryId

    override fun setId(id: Long) {
        categoryId = id
    }
}