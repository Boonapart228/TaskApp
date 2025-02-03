package com.example.taskapp.data.local

import com.example.taskapp.domain.model.Task

val taskManager = mutableMapOf(
    "Магазин" to mutableListOf(
        Task("Молоко", "Треба купити молока", false),
        Task("Крупа", "Треба купити крупу", false)
    ),
    "СТО" to mutableListOf(
        Task("Авто", "Авто -> СТО", false))
)