package com.example.taskapp.domain

interface TitleFormatter {
    fun getCorrectTitle(title: String): String
}