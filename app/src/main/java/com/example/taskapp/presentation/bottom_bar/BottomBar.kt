package com.example.taskapp.presentation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.bottom_bar.model.BottomBarItems

@Composable
fun BottomBar() {
    var selectedItem by rememberSaveable { mutableStateOf(BottomBarItems.HOME) }

    NavigationBar {
        BottomBarItems.entries.forEach {
            if (it != BottomBarItems.DEFAULT) {
                NavigationBarItem(
                    selected = selectedItem == it,
                    onClick = { selectedItem = it },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            stringResource(it.textId),
                            fontWeight = if (selectedItem == it) FontWeight.Medium else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
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
    BottomBar()
}