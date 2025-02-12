package com.example.taskapp.domain

interface CategoryIdStorage {
    fun setId(id: Long)
    fun getId(): Long?
}