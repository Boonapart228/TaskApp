package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun HomeScreenBar(
    onToggleSearchBar: () -> Unit,
    onNavigateToSettingScreen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(
            top = LocalDimen.current.rowTopBarPaddingTop,
            start = LocalDimen.current.rowTopBarPaddingStart
        )
    ) {
        Text(
            text = stringResource(id = R.string.home_title),
            modifier = Modifier.weight(LocalProperty.current.eightyPercent),
            style = TextStyle(
                fontSize = LocalDimen.current.textTopBarTitle,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.background
            )
        )
        IconButton(
            onClick = onToggleSearchBar,
            modifier = Modifier.weight(LocalProperty.current.tenPercent)
        ) {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }
        IconButton(
            onClick = onNavigateToSettingScreen,
            modifier = Modifier.weight(LocalProperty.current.tenPercent)
        ) {
            Icon(
                imageVector = Icons.Default.Settings, contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}