package com.example.taskapp.presentation.setting_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.taskapp.presentation.setting_screen.components.SettingContent
import com.example.taskapp.presentation.setting_screen.components.SettingNavigationEvent
import com.example.taskapp.presentation.setting_screen.components.SettingViewModel

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    navigationToHomeScreen: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                SettingNavigationEvent.NavigationToHome -> navigationToHomeScreen()
            }
        }
    }

    SettingContent(
        state = state,
        onChangeAppThemeClick = viewModel::onChangeAppThemeClick,
        onToggleLanguageMenu = viewModel::onToggleLanguageMenu,
        setLanguage = viewModel::setLanguage,
        onNavigationClick = viewModel::onNavigationClick
    )
}