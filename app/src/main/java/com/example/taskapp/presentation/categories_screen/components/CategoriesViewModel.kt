package com.example.taskapp.presentation.categories_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.presentation.navigation.model.Screens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CategoriesNavigationEvent>()
    val event = _event.asSharedFlow()


    fun onToggleDialogClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(showDialogCreateCategory = !it.showDialogCreateCategory)
            }
        }
    }

    fun createCategory() {
        val categoriesList = _state.value.listCategory.toMutableList()
        categoriesList += _state.value.categoriesTitle
        viewModelScope.launch {
            _state.update {
                it.copy(
                    listCategory = categoriesList,
                    categoriesTitle = ""
                )
            }
        }
    }

    fun clearCategoryTitle() {
        viewModelScope.launch {
            _state.update {
                it.copy(categoriesTitle = "")
            }
        }
    }

    fun onCategoryTitleChange(categoriesTitle: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(categoriesTitle = categoriesTitle)
            }
        }
    }

    fun onBottomBarNavigationClick(route: Screens) {
        when (route) {
            Screens.HOME_SCREEN -> viewModelScope.launch { _event.emit(CategoriesNavigationEvent.NavigationToHome) }
            Screens.CATEGORIES_SCREEN -> {}
        }
    }
}