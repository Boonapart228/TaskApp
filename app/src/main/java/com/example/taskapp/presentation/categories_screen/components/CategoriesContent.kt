package com.example.taskapp.presentation.categories_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.presentation.bottom_bar.BottomBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesAddButton
import com.example.taskapp.presentation.categories_screen.contents.CategoriesColorPicker
import com.example.taskapp.presentation.categories_screen.contents.CategoriesDeleteDialog
import com.example.taskapp.presentation.categories_screen.contents.CategoriesDetails
import com.example.taskapp.presentation.categories_screen.contents.CategoriesDialog
import com.example.taskapp.presentation.categories_screen.contents.CategoriesListBar
import com.example.taskapp.presentation.categories_screen.contents.CategoriesTopBar
import com.example.taskapp.presentation.categories_screen.model.CategorySortParameter
import com.example.taskapp.presentation.navigation.model.Screens

@Composable
fun CategoriesContent(
    state: CategoriesState,
    onToggleDialogCreateClick: () -> Unit,
    onBottomBarNavigationClick: (Screens) -> Unit,
    onCategoryTitleChange: (String) -> Unit,
    handleCategory: () -> Unit,
    clearCategoryTitle: () -> Unit,
    onDeleteCategoryClick: () -> Unit,
    onEditCategoryClick: (Long) -> Unit,
    onToggleDialogEditClick: () -> Unit,
    onToggleDeleteCategoryClick: () -> Unit,
    onCategorySelectClick: (Long) -> Unit,
    onSelectColorClick: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    onBackClick: () -> Unit,
    setCurrentCategory: (Long) -> Unit,
    onCategoryDirectionChange: (SortDirection) -> Unit,
    onCategorySortParameterChange: (CategorySortParameter) -> Unit,
    onToggleCategoryMenuClick: () -> Unit,
    onToggleAllLines: () -> Unit,
    onSetSearchTitle: (String) -> Unit,
    onToggleSearchBar: () -> Unit
) {
    Scaffold(topBar = {
        CategoriesTopBar(
            searchTitle = state.searchTitle,
            showSearchBar = state.showSearchBar,
            onSetSearchTitle = onSetSearchTitle,
            onToggleSearchBar = onToggleSearchBar
        )
    },
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
            CategoriesListBar(
                textId = R.string.categories_list_text,
                expanded = state.showDialogCategorySort,
                selectedSortParameter = state.categorySortParameter,
                selectedSortDirection = state.categorySortDirection,
                onAscendingSortClick = { onCategoryDirectionChange(SortDirection.ASCENDING) },
                onDescendingSortClick = { onCategoryDirectionChange(SortDirection.DESCENDING) },
                onSortByNoteCountClick = { onCategorySortParameterChange(CategorySortParameter.NOTE) },
                onSortByTitleClick = { onCategorySortParameterChange(CategorySortParameter.TITLE) },
                onToggleMenuClick = { onToggleCategoryMenuClick() }
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                ) {
                items(state.categories.filter {
                    it.categoryTitle.lowercase().contains(state.searchTitle.lowercase())
                }) { category ->
                    CategoriesDetails(
                        categoriesTitle = category.categoryTitle,
                        sizeNote = category.taskCount,
                        allLines = state.allLines,
                        hexColorCode = category.categoryHexColorCode,
                        onDeleteCategory = {
                            onToggleDeleteCategoryClick()
                            setCurrentCategory(category.categoryId)
                        },
                        onEditCategory = {
                            onToggleDialogEditClick()
                            onEditCategoryClick(category.categoryId)
                        },
                        onCategorySelectClick = {
                            onCategorySelectClick(category.categoryId)
                        },
                        onToggleAllLines = onToggleAllLines
                    )
                }
            }
            if (state.showDialogCategory) {
                CategoriesDialog(
                    value = state.categoryTitle,
                    onValueChange = onCategoryTitleChange,
                    clearCategoryTitle = clearCategoryTitle,
                    onConfirmClick = onConfirmClick,
                    onDismissClick = onDismissClick
                )
            }
            if (state.showDialogColorPicker) {
                CategoriesColorPicker(
                    hexColorCode = state.hexColorCode,
                    categoryOperation = state.categoryOperation,
                    onSelectColorClick = onSelectColorClick,
                    onBackClick = onBackClick,
                    handleCategory = handleCategory,
                    clearCategoryTitle = clearCategoryTitle,
                    onDismissClick = onDismissClick
                )
            }
            if (state.showDialogDeleteCategory) {
                CategoriesDeleteDialog(
                    onDismissRequest = { onToggleDeleteCategoryClick() },
                    onConfirmation = {
                        onDeleteCategoryClick()
                        onToggleDeleteCategoryClick()
                    }
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
        onBottomBarNavigationClick = {},
        onCategoryTitleChange = {},
        handleCategory = {},
        clearCategoryTitle = {},
        onDeleteCategoryClick = {},
        onEditCategoryClick = {},
        onToggleDialogEditClick = {},
        onCategorySelectClick = {},
        onSelectColorClick = {},
        onConfirmClick = {},
        onDismissClick = {},
        onBackClick = {},
        onToggleDeleteCategoryClick = {},
        setCurrentCategory = {},
        onCategoryDirectionChange = {},
        onToggleCategoryMenuClick = {},
        onCategorySortParameterChange = {},
        onSetSearchTitle = {},
        onToggleSearchBar = {},
        onToggleAllLines = {}
    )
}