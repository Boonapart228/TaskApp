package com.example.taskapp.presentation.setting_screen.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.AppSettings
import com.example.taskapp.presentation.navigation.model.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val appSettings: AppSettings) : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(darkTheme = appSettings.getAppTheme())
            }
        }
    }

    fun onChangeAppThemeClick() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    darkTheme = !it.darkTheme
                )
            }
            appSettings.setAppTheme(_state.value.darkTheme)
        }
    }

    fun onNavigationClick(screens: Screens) {
        when (screens) {
            Screens.HOME_SCREEN -> {
                viewModelScope.launch { _event.emit(SettingNavigationEvent.NavigationToHome) }
            }

            Screens.CATEGORIES_SCREEN -> {}
            Screens.TASK_EDITOR_SCREEN -> {}
            Screens.SETTING_SCREEN -> {}
        }
    }

}