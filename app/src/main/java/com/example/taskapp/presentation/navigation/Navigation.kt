package com.example.taskapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskapp.presentation.categories_screen.CategoriesScreen
import com.example.taskapp.presentation.categories_screen.components.CategoriesViewModel
import com.example.taskapp.presentation.home_screen.HomeScreen
import com.example.taskapp.presentation.home_screen.components.HomeViewModel
import com.example.taskapp.presentation.navigation.model.Screens

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    NavigationHost(navHostController = navHostController)
}

@Composable
private fun NavigationHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.HOME_SCREEN.route) {
        composable(route = Screens.HOME_SCREEN.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                navigateToCategories = { navHostController.navigate(Screens.CATEGORIES_SCREEN.route) }
            )
        }
        composable(route = Screens.CATEGORIES_SCREEN.route) {
            val viewModel: CategoriesViewModel = hiltViewModel()
            CategoriesScreen(
                viewModel = viewModel,
                navigateToHome = { navHostController.navigate(Screens.HOME_SCREEN.route) }
            )
        }
    }
}