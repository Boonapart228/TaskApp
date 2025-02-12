package com.example.taskapp.presentation.home_screen.components

import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.navigation.model.Screens

data class HomeState(
    val selectedScreen: Screens = Screens.HOME_SCREEN,
    val tasks: List<Task> = listOf()
)