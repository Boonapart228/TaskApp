package com.example.taskapp.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalProperty = compositionLocalOf {
    Property()
}

data class Property(
    val tenPercent: Float = 1f,
    val eightyPercent: Float = 8f,
    val rotation270: Float = 270f,
    val rotation90: Float = 90f,
    val ninetyPercent: Float = 9f,
    val offSetByX: Int = -6,
    val offSetByY: Int = 0,
    val offSetByYAscendingIconButton: Int = -2,
    val offSetByYDescendingIconButton: Int = -6,
    val offSetByXDescendingIcon: Int = -4,
    val offSetByYTextButton: Int = -10,
    val offSetByXRow: Int = 20,
    val offSetByXTextButton: Int = -10,
)