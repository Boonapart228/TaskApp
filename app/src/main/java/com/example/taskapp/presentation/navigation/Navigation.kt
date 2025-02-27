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
import com.example.taskapp.presentation.setting_screen.SettingScreen
import com.example.taskapp.presentation.setting_screen.components.SettingViewModel
import com.example.taskapp.presentation.task_editor_screen.TaskEditorScreen
import com.example.taskapp.presentation.task_editor_screen.components.TaskEditorViewModel

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    val settingViewModel: SettingViewModel = hiltViewModel()
    NavigationHost(
        navHostController = navHostController,
        settingViewModel = settingViewModel
    )
}

@Composable
private fun NavigationHost(
    navHostController: NavHostController,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HOME_SCREEN.route
    ) {
        composable(route = Screens.HOME_SCREEN.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                navigateToCategories = { navHostController.navigate(Screens.CATEGORIES_SCREEN.route) },
                navigationToTaskEditor = { navHostController.navigate(Screens.TASK_EDITOR_SCREEN.route) },
                navigateToSettings = { navHostController.navigate(Screens.SETTING_SCREEN.route) }
            )
        }
        composable(route = Screens.CATEGORIES_SCREEN.route) {
            val viewModel: CategoriesViewModel = hiltViewModel()
            CategoriesScreen(
                viewModel = viewModel,
                navigateToHome = { navHostController.navigate(Screens.HOME_SCREEN.route) }
            )
        }
        composable(route = Screens.TASK_EDITOR_SCREEN.route) {
            val viewModel: TaskEditorViewModel = hiltViewModel()
            TaskEditorScreen(viewModel = viewModel,
                navigationToHomeScreen = { navHostController.navigate(Screens.HOME_SCREEN.route) })
        }
        composable(route = Screens.SETTING_SCREEN.route) {
            SettingScreen(viewModel = settingViewModel,
                navigationToHomeScreen = { navHostController.navigate(Screens.HOME_SCREEN.route) })
        }
    }
}