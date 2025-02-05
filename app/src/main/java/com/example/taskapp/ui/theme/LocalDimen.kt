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
    val textTopBarHorizontalPadding: Dp = 4.dp,
    val rowCategoriesAllPadding: Dp = 12.dp,
    val rowCategoriesHorizontalPadding: Dp = 8.dp,
    val textCategoriesTitle: TextUnit = 24.sp,
    val textCategoriesSortType: TextUnit = 14.sp,
    val categoriesSpacerHorizontalPadding: Dp = 4.dp,
    val categoriesIconButtonSize: Dp = 10.dp,
    val categoriesIconSize: Dp = 10.dp,
    val categoriesIconOffset: Dp = 2.dp,
    val categoriesCardHorizontalPadding: Dp = 16.dp,
    val categoriesCardVerticalPadding: Dp = 8.dp,
    val categoriesCardShape: Dp = 18.dp,
    val categoriesColumnSpaceBy: Dp = 6.dp,
    val categoriesColumnPaddingAll: Dp = 12.dp,
    val categoriesIconSizeLarge: Dp = 86.dp,
    val categoriesTextSize: TextUnit = 18.sp,
    val categoriesTextSizeSmall: TextUnit = 12.sp,
    val categoriesTextBottomPadding: Dp = 2.dp,
    val cardHorizontalPadding: Dp = 18.dp,
    val cardVerticalPadding: Dp = 24.dp,
    val cardElevation: Dp = 18.dp,
    val cardShape: Dp = 12.dp,
    val iconMediumSize: Dp = 28.dp,
    val textCategoriesTitleMediumSize: TextUnit = 18.sp,
    val verticalGridHorizontalPadding: Dp = 4.dp,
    val verticalGridVerticalPadding: Dp = 8.dp,
    val homeIconFABSize: Dp = 32.dp,
    val homeColumnPaddingTop: Dp = 24.dp,
    val lazyVerticalGridHeight: Dp = 180.dp,
    val homeSpacerPaddingTop: Dp = 12.dp,
    val homeTextCategoryTitle: TextUnit = 20.sp,
    val homeDetailsCardHorizontalPadding: Dp = 10.dp,
    val homeDetailsCardVerticalPadding: Dp = 12.dp,
    val homeDetailsHorizontalSpacedBy: Dp = 2.dp,
    val homeDetailsIconSize: Dp = 10.dp,
    val homeDetailsDataTextSize: TextUnit = 10.sp,
    val taskEditorColumnTopPadding: Dp = 20.dp,
    val taskEditorTaskTitleSize: TextUnit = 22.sp,
    val taskEditorTaskDescriptionSize: TextUnit = 18.sp,
    val taskEditorTextFieldPaddingAll: Dp = 10.dp
)