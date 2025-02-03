package com.example.taskapp.presentation.categories_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.bottom_bar.BottomBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesAddButton
import com.example.taskapp.presentation.categories_screen.contents.CategoriesListBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesTopBar
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun CategoriesContent(
    state: CategoriesState,
    onBottomBarNavigationClick: (Screens) -> Unit
) {
    Scaffold(topBar = { CategoriesTopBar() },
        bottomBar = {
            BottomBar(
                onClick = onBottomBarNavigationClick,
                selectedNavigate = state.selectedScreen
            )
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesAddButton()
            CategoriesListBar()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    horizontal = LocalDimen.current.verticalGridHorizontalPadding,
                    vertical = LocalDimen.current.verticalGridVerticalPadding
                ),

                ) {}
        }

    }
}


@Composable
@Preview
fun CategoriesContentPreview() {
    CategoriesContent(state = CategoriesState()) {
    }
}