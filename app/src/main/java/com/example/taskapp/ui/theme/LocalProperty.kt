package com.example.taskapp.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val tenPercent: Float = 1f,
    val eightyPercent: Float = 8f,
)