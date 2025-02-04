package com.example.taskapp.presentation.categories_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.presentation.bottom_bar.BottomBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesAddButton
import com.example.taskapp.presentation.categories_screen.contents.CategoriesCreationDialog
import com.example.taskapp.presentation.categories_screen.contents.CategoriesDetails
import com.example.taskapp.presentation.categories_screen.contents.CategoriesListBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesTopBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesUpdateDialog
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun CategoriesContent(
    state: CategoriesState,
    onToggleDialogCreateClick: () -> Unit,
    onBottomBarNavigationClick: (Screens) -> Unit,
    onCategoryTitleChange: (String) -> Unit,
    createCategory: () -> Unit,
    clearCategoryTitle: () -> Unit,
    onDeleteCategoryClick: (String) -> Unit,
    onEditCategoryClick: (String) -> Unit,
    onToggleDialogUpdateClick: () -> Unit,
    updateCategory: () -> Unit
) {
    Scaffold(topBar = { CategoriesTopBar() },
        bottomBar = {
            BottomBar(
                onClick = onBottomBarNavigationClick,
                selectedNavigate = state.selectedScreen
            )
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesAddButton(
                onToggleDialogClick = onToggleDialogCreateClick
            )
            CategoriesListBar()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    horizontal = LocalDimen.current.verticalGridHorizontalPadding,
                    vertical = LocalDimen.current.verticalGridVerticalPadding
                ),

                ) {
                items(state.taskManagerItems.toList(), key = { it }) { category ->
                    CategoriesDetails(
                        categoriesTitle = category.first,
                        sizeNote = category.second.size,
                        onDeleteCategory = { onDeleteCategoryClick(category.first) },
                        onEditCategory = {
                            onToggleDialogUpdateClick()
                            onEditCategoryClick(category.first)
                        }
                    )
                }
            }
            if (state.showDialogCreateCategory) {
                CategoriesCreationDialog(
                    value = state.categoryTitle,
                    onValueChange = onCategoryTitleChange,
                    onToggleDialogClick = onToggleDialogCreateClick,
                    createCategory = createCategory,
                    clearCategoryTitle = clearCategoryTitle,
                )
            }
            if (state.showDialogUpdateCategory) {
                CategoriesUpdateDialog(
                    value = state.categoryTitle,
                    onValueChange = onCategoryTitleChange,
                    onToggleDialogClick = onToggleDialogUpdateClick,
                    updateCategory = updateCategory,
                    clearCategoryTitle = clearCategoryTitle,
                )
            }
        }

    }
}


@Composable
@Preview
fun CategoriesContentPreview() {
    CategoriesContent(
        state = CategoriesState(),
        onToggleDialogCreateClick = {},
        onCategoryTitleChange = {},
        onBottomBarNavigationClick = {},
        createCategory = {},
        clearCategoryTitle = {},
        onDeleteCategoryClick = {},
        onToggleDialogUpdateClick = {},
        onEditCategoryClick = {},
        updateCategory = {})
}