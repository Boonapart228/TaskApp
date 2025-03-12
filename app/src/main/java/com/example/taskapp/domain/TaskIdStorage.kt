package com.example.taskapp.domain

interface TaskIdStorage {
    fun setId(id: Long?)
    fun getId(): Long?
}