package com.example.taskapp.presentation.setting_screen.contents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean,
    onChangeAppThemeClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(LocalDimen.current.settingsRowPaddingAll)
    ) {
        Text(
            text = stringResource(id = R.string.switch_theme_text),
            fontSize = LocalDimen.current.settingsTextSize,
            modifier = Modifier.weight(
                LocalProperty.current.eightyPercent
            )
        )
        Switch(
            checked = darkTheme,
            onCheckedChange = { onChangeAppThemeClick() },
            modifier = Modifier.weight(LocalProperty.current.twentyPercent)
        )
    }
}