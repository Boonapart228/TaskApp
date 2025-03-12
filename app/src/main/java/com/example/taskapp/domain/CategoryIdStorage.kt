package com.example.taskapp.domain

interface CategoryIdStorage {
    suspend fun setId(id: Long?)
    suspend fun getId(): Long?
}