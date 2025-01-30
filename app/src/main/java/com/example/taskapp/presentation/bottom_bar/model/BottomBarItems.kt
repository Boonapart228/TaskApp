package com.example.taskapp.presentation.bottom_bar.model

import com.example.taskapp.R

enum class BottomBarItems(
    val route: String = "",
    val icon: Int = R.drawable.home,
    val textId: Int = R.string.empty_text
) {
    HOME(route = "Home_Screen", icon = R.drawable.home, textId = R.string.home_bottom_bar_text),
    CATEGORIES(
        route = "Categories_Screen",
        icon = R.drawable.list,
        textId = R.string.categories_bottom_bar_text
    ),
    DEFAULT
}