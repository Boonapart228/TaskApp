package com.example.taskapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDimen = compositionLocalOf {
    Dimensions()
}


data class Dimensions(
    val columnTopBarPadding: Dp = 8.dp,
    val columnTopBarPaddingTop: Dp = 18.dp,
    val columnTopBarPaddingBottom: Dp = 4.dp,
    val rowTopBarPaddingTop: Dp = 4.dp,
    val rowTopBarPaddingStart: Dp = 6.dp,
    val textTopBarTitle: TextUnit = 32.sp,
    val iconButtonTopBarOffsetByX: Dp = 4.dp,
    val rowTopBarPaddingAll: Dp = 2.dp,
    val rowTopBarShape: Dp = 18.dp,
    val textButtonTopBarHeight: Dp = 32.dp,
    val textButtonTopBarShape: Dp = 18.dp,
    val textTopBar: TextUnit = 14.sp,
    val textTopBarHorizontalPadding: Dp = 4.dp
)