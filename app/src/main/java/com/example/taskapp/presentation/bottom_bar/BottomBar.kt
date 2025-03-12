package com.example.taskapp.presentation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.bottom_bar.model.BottomBarItems
import com.example.taskapp.presentation.navigation.model.Screens

@Composable
fun BottomBar(
    onClick: (Screens) -> Unit,
    selectedNavigate: Screens
) {
    NavigationBar {
        BottomBarItems.entries.forEach {
            if (it != BottomBarItems.DEFAULT) {
                NavigationBarItem(
                    selected = it.route == selectedNavigate,
                    onClick = { onClick(it.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            stringResource(it.textId),
                            fontWeight = if (selectedNavigate == it.route) FontWeight.Medium else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedIconColor = MaterialTheme.colorScheme.background,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun BottomBarPreview() {
    BottomBar(
        onClick = {},
        selectedNavigate = Screens.HOME_SCREEN
    )
}