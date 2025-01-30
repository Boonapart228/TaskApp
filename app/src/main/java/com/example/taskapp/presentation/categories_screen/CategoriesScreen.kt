package com.example.taskapp.presentation.categories_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.taskapp.presentation.categories_screen.components.CategoriesContent
import com.example.taskapp.presentation.categories_screen.components.CategoriesNavigationEvent
import com.example.taskapp.presentation.categories_screen.components.CategoriesViewModel

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel,
    navigateToHome: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                CategoriesNavigationEvent.NavigationToHome -> navigateToHome()
            }
        }
    }

    CategoriesContent(
        state = state,
        onBottomBarNavigationClick = viewModel::onBottomBarNavigationClick
    )
}