package com.example.taskapp.presentation.setting_screen.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.usecase.settings.GetAppThemeUseCase
import com.example.taskapp.domain.usecase.settings.GetLanguageUseCase
import com.example.taskapp.domain.usecase.settings.GetRecentNoteFilterUseCase
import com.example.taskapp.domain.usecase.settings.SetAppThemeUseCase
import com.example.taskapp.domain.usecase.settings.SetLanguageUseCase
import com.example.taskapp.domain.usecase.settings.SetRecentNoteFilterUseCase
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.presentation.setting_screen.models.Language
import com.example.taskapp.presentation.setting_screen.models.RecentNoteFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getAppThemeUseCase: Provider<GetAppThemeUseCase>,
    private val getLanguageUseCase: Provider<GetLanguageUseCase>,
    private val getRecentNoteFilterUseCase: Provider<GetRecentNoteFilterUseCase>,
    private val setAppThemeUseCase: Provider<SetAppThemeUseCase>,
    private val setLanguageUseCase: Provider<SetLanguageUseCase>,
    private val setRecentNoteFilterUseCase: Provider<SetRecentNoteFilterUseCase>
) : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SettingNavigationEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    darkTheme = getAppThemeUseCase.get().execute(),
                    languageCode = getLanguageUseCase.get().execute().languageCode,
                    languageTextId = getLanguageUseCase.get().execute().languageTextId,
                    recentNoteFilterTextId = getRecentNoteFilterUseCase.get().execute().textId
                )
            }
            switchTheme(_state.value.darkTheme)
        }
    }

    private fun switchTheme(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun onChangeAppThemeClick() {
        viewModelScope.launch {
            val newDarkThemeState = !_state.value.darkTheme
            _state.update {
                it.copy(darkTheme = newDarkThemeState)
            }
            setAppThemeUseCase.get().execute(newDarkThemeState)
            switchTheme(newDarkThemeState)
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

    fun onToggleLanguageMenu() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    expandedLanguageMenu = !it.expandedLanguageMenu
                )
            }
        }
    }

    fun setLanguage(language: Language) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    languageCode = language.languageCode,
                    languageTextId = language.languageTextId
                )
            }
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    language.languageCode
                )
            )
            setLanguageUseCase.get().execute(language)
        }
    }

    fun onToggleRecentNoteMenu() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    expandedRecentNoteMenu = !it.expandedRecentNoteMenu
                )
            }
        }
    }

    fun setRecentNoteFilter(recentNoteFilter: RecentNoteFilter) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    recentNoteFilterTextId = recentNoteFilter.textId
                )
            }
            setRecentNoteFilterUseCase.get().execute(recentNoteFilter)

        }
    }

}