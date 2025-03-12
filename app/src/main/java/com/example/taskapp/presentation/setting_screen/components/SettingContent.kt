package com.example.taskapp.presentation.setting_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.presentation.setting_screen.contents.SettingLanguage
import com.example.taskapp.presentation.setting_screen.contents.SettingRecentNote
import com.example.taskapp.presentation.setting_screen.contents.SettingTopBar
import com.example.taskapp.presentation.setting_screen.contents.ThemeSwitcher
import com.example.taskapp.presentation.setting_screen.models.Language
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter
import com.example.taskapp.ui.theme.AppTheme

@Composable
fun SettingContent(
    state: SettingState,
    onChangeAppThemeClick: () -> Unit,
    onToggleLanguageMenu: () -> Unit,
    onToggleRecentNoteMenu: () -> Unit,
    setLanguage: (Language) -> Unit,
    setRecentNoteFilter: (RecentNoteFilter) -> Unit,
    onNavigationClick: (Screens) -> Unit
) {
    Scaffold(topBar = { SettingTopBar(onNavigateBack = { onNavigationClick(Screens.HOME_SCREEN) }) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ThemeSwitcher(
                darkTheme = state.darkTheme,
                onChangeAppThemeClick = onChangeAppThemeClick
            )
            HorizontalDivider()
            SettingLanguage(
                languageTextId = state.languageTextId,
                expandedLanguageMenu = state.expandedLanguageMenu,
                onToggleLanguageMenu = onToggleLanguageMenu,
                setLanguage = setLanguage
            )
            HorizontalDivider()
            SettingRecentNote(
                expandedRecentNoteMenu = state.expandedRecentNoteMenu,
                recentNoteFilter = state.recentNoteFilterTextId,
                onToggleRecentNoteMenu = onToggleRecentNoteMenu,
                setRecentNoteFilter = setRecentNoteFilter
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun SettingContentPreview() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        SettingContent(
            state = SettingState(),
            onChangeAppThemeClick = {},
            onNavigationClick = {},
            onToggleLanguageMenu = {},
            setLanguage = {},
            onToggleRecentNoteMenu = {},
            setRecentNoteFilter = {})
    }
}