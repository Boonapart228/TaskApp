package com.example.taskapp.presentation.bottom_bar.model

import com.example.taskapp.R
import com.example.taskapp.presentation.navigation.model.Screens

enum class BottomBarItems(
    val route: Screens = Screens.HOME_SCREEN,
    val icon: Int = R.drawable.home,
    val textId: Int = R.string.empty_text
) {
    HOME(
        route = Screens.HOME_SCREEN,
        icon = R.drawable.home,
        textId = R.string.home_bottom_bar_text
    ),
    CATEGORIES(
        route = Screens.CATEGORIES_SCREEN,
        icon = R.drawable.list,
        textId = R.string.categories_bottom_bar_text
    ),
    DEFAULT
}