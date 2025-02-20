package com.example.taskapp.presentation.categories_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.taskapp.R
import com.example.taskapp.presentation.categories_screen.components.CategoriesContent
import com.example.taskapp.presentation.categories_screen.components.CategoriesMessageEvent
import com.example.taskapp.presentation.categories_screen.components.CategoriesNavigationEvent
import com.example.taskapp.presentation.categories_screen.components.CategoriesViewModel

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel,
    navigateToHome: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                CategoriesNavigationEvent.NavigationToHome -> navigateToHome()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.messageEvent.collect {
            when (it) {
                CategoriesMessageEvent.CategoryCreationSuccess -> {
                    Toast.makeText(
                        context,
                        R.string.category_success_create_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                CategoriesMessageEvent.CategoryDeletionSuccess -> {
                    Toast.makeText(
                        context,
                        R.string.category_success_delete_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                CategoriesMessageEvent.CategoryUpdateSuccess -> {
                    Toast.makeText(
                        context,
                        R.string.category_success_update_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                CategoriesMessageEvent.CategoryTitleCannotBeEmpty -> {
                    Toast.makeText(
                        context,
                        R.string.category_title_empty_text,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    CategoriesContent(
        state = state,
        onToggleDialogCreateClick = viewModel::onToggleDialogCreateClick,
        onBottomBarNavigationClick = viewModel::onNavigationClick,
        onCategoryTitleChange = viewModel::onCategoryTitleChange,
        handleCategory = viewModel::handleCategory,
        clearCategoryTitle = viewModel::clearCategoryTitle,
        onDeleteCategoryClick = viewModel::onDeleteCategoryClick,
        onEditCategoryClick = viewModel::onEditCategoryClick,
        onToggleDialogEditClick = viewModel::onToggleDialogEditClick,
        onCategorySelectClick = viewModel::onCategorySelectClick,
        onSelectColorClick = viewModel::onSelectColorClick,
        onConfirmClick = viewModel::onConfirmClick,
        onDismissClick = viewModel::onDismissClick,
        onBackClick = viewModel::onBackClick,
        onToggleDeleteCategoryClick = viewModel::onToggleDeleteCategoryClick,
        setCurrentCategory = viewModel::setCurrentCategory
    )
}