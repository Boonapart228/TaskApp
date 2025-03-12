package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesTopBar(
    showSearchBar: Boolean,
    searchTitle: String,
    onToggleSearchBar: () -> Unit,
    onSetSearchTitle: (String) -> Unit
) {
    AnimatedVisibility(
        visible = !showSearchBar,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.categories_title),
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.background
                )
            },
            actions = {
                IconButton(onClick = onToggleSearchBar) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
    AnimatedVisibility(
        visible = showSearchBar,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        TopAppBar(
            title = {
                CategoriesSearchBar(
                    searchTitle = searchTitle,
                    onToggleSearchBar = onToggleSearchBar,
                    onSetSearchTitle = onSetSearchTitle
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}

@Preview
@Composable
fun CategoriesTopBarPreview() {
    AppTheme(dynamicColor = false) {
        CategoriesTopBar(showSearchBar = false,
            searchTitle = "",
            onToggleSearchBar = {},
            onSetSearchTitle = {})
    }
}