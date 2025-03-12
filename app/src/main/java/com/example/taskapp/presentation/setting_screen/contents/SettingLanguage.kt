package com.example.taskapp.presentation.setting_screen.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.setting_screen.models.Language
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingLanguage(
    expandedLanguageMenu: Boolean,
    languageTextId: Int,
    onToggleLanguageMenu: () -> Unit,
    setLanguage: (Language) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(LocalDimen.current.settingsRowPaddingAll)
    ) {
        Text(
            text = stringResource(id = R.string.select_language),
            fontSize = LocalDimen.current.settingsTextSize,
            modifier = Modifier
                .fillMaxWidth()
                .weight(LocalProperty.current.tenPercent)
        )
        ExposedDropdownMenuBox(
            expanded = expandedLanguageMenu,
            onExpandedChange = { onToggleLanguageMenu() }
        )
        {
            TextField(
                readOnly = true,
                value = stringResource(id = languageTextId),
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(LocalDimen.current.textFieldWidth)
                    .menuAnchor()
                    .clickable { onToggleLanguageMenu() }
            )
            ExposedDropdownMenu(
                expanded = expandedLanguageMenu,
                onDismissRequest = { onToggleLanguageMenu() }) {
                Language.entries.forEach { element ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = element.languageTextId),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            onToggleLanguageMenu()
                            setLanguage(element)
                        })
                    if (element != Language.entries.last()) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SettingLanguagePreview() {
    SettingLanguage(
        languageTextId = R.string.english_language_text,
        expandedLanguageMenu = false,
        onToggleLanguageMenu = { },
        setLanguage = {})
}