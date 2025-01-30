package com.example.taskapp.presentation.bottom_bar.model

import com.example.taskapp.R

enum class BottomBarItems(val route: String, val icon: Int, val text: String) {
    HOME(route = "Home_Screen", icon = R.drawable.home, text = "Home"),
    CATEGORIES(
        route = "Categories_Screen",
        icon = R.drawable.list,
        text = "Categories"
    ),
    DEFAULT("", R.drawable.home, "")
}