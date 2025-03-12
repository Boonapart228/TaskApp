package com.example.taskapp.presentation.home_screen.components

sealed class HomeMessageEvent {
    data object ChoseCategory : HomeMessageEvent()
}