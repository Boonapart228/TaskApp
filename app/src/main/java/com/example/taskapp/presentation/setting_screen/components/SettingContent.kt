package com.example.taskapp.presentation.setting_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.presentation.setting_screen.contents.SettingTopBar
import com.example.taskapp.ui.theme.AppTheme
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun SettingContent(
    state: SettingState,
    onChangeAppThemeClick: () -> Unit,
    onNavigationClick: (Screens) -> Unit
) {
    Scaffold(topBar = { SettingTopBar(onNavigateBack = { onNavigationClick(Screens.HOME_SCREEN) }) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(LocalDimen.current.settingsRowPaddingAll)
            ) {
                Text(
                    text = "Change App Theme",
                    fontSize = LocalDimen.current.settingsTextSize,
                    modifier = Modifier.weight(
                        LocalProperty.current.eightyPercent
                    )
                )
                Switch(
                    checked = state.darkTheme,
                    onCheckedChange = { onChangeAppThemeClick() },
                    modifier = Modifier.weight(LocalProperty.current.twentyPercent)
                )
            }
            HorizontalDivider()
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
            onNavigationClick = {})
    }
}