package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.home_screen.model.NotesFilterType
import com.example.taskapp.ui.theme.AppTheme
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun HomeTopBar(
    searchTitle: String,
    showSearchBar: Boolean,
    notesFilterType: NotesFilterType,
    onChangeGridColumnsClick: () -> Unit,
    onNavigateToSettingScreen: () -> Unit,
    onToggleSearchBar: () -> Unit,
    onSetSearchTitle: (String) -> Unit,
    onChangeNoteFilterType: (NotesFilterType) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(
                LocalDimen.current.columnTopBarPadding,
                top = LocalDimen.current.columnTopBarPaddingTop,
                bottom = LocalDimen.current.columnTopBarPaddingBottom
            )
    ) {
        AnimatedVisibility(!showSearchBar) {
            HomeScreenBar(
                onToggleSearchBar = onToggleSearchBar,
                onNavigateToSettingScreen = onNavigateToSettingScreen
            )
        }
        AnimatedVisibility(showSearchBar) {
                HomeSearchBar(
                    searchTitle = searchTitle,
                    onToggleSearchBar = onToggleSearchBar,
                    onSetSearchTitle = onSetSearchTitle,
                    onNavigateToSettingScreen = onNavigateToSettingScreen
                )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeNoteSwitcher(
                notesFilterType = notesFilterType,
                setNoteFilterType = onChangeNoteFilterType
            )
            IconButton(
                onClick = onChangeGridColumnsClick,
                modifier = Modifier.offset(x = LocalDimen.current.iconButtonTopBarOffsetByX)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.grid_ui),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeTopBarPreview() {
    AppTheme(dynamicColor = false) {
        HomeTopBar(
            searchTitle = "",
            showSearchBar = false,
            notesFilterType = NotesFilterType.ALL,
            onChangeGridColumnsClick = {},
            onNavigateToSettingScreen = {},
            onChangeNoteFilterType = {},
            onToggleSearchBar = {},
            onSetSearchTitle = {}
        )
    }
}