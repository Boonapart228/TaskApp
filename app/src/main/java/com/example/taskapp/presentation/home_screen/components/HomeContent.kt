package com.example.taskapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.bottom_bar.BottomBar
import com.example.taskapp.presentation.home_screen.contents.HomeListBar
import com.example.taskapp.presentation.home_screen.contents.HomeTaskDetails
import com.example.taskapp.presentation.home_screen.contents.HomeTopBar
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.presentation.home_screen.model.HomeSortParameter
import com.example.taskapp.presentation.home_screen.model.NotesFilterType
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.ui.theme.LocalDimen


@Composable
fun HomeContent(
    state: HomeState,
    onNavigationClick: (Screens) -> Unit,
    onChangeGridColumnsClick: () -> Unit,
    onTogglePinnedMenuClick: () -> Unit,
    onToggleUnPinnedMenuClick: () -> Unit,
    onNavigateToTaskEditorClick: () -> Unit,
    formatTime: (Long) -> String,
    formatDate: (Long) -> String,
    onTaskSelectClick: (Long) -> Unit,
    onPinnedDirectionChange: (SortDirection) -> Unit,
    onPinnedSortParameterChange: (HomeSortParameter) -> Unit,
    onUnPinnedDirectionChange: (SortDirection) -> Unit,
    onUnPinnedSortParameterChange: (HomeSortParameter) -> Unit,
    onChangeNoteFilterType: (NotesFilterType) -> Unit
) {
    Scaffold(topBar = {
        HomeTopBar(
            notesFilterType = state.notesFilterType,
            onChangeGridColumnsClick = onChangeGridColumnsClick,
            onNavigateToSettingScreen = { onNavigationClick(Screens.SETTING_SCREEN) },
            onChangeNoteFilterType = onChangeNoteFilterType
        )
    },
        bottomBar = {
            BottomBar(
                onClick = onNavigationClick,
                selectedNavigate = state.selectedScreen
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToTaskEditorClick,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.homeIconFABSize)
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = LocalDimen.current.homeColumnPaddingTop)
        ) {
            HomeListBar(
                textId = R.string.pinned_notes_text,
                expanded = state.showPinnedSortDialog,
                selectedHomeSortParameter = state.pinnedHomeSortParameter,
                selectedSortDirection = state.pinnedSortDirection,
                onAscendingSortClick = { onPinnedDirectionChange(SortDirection.ASCENDING) },
                onDescendingSortClick = { onPinnedDirectionChange(SortDirection.DESCENDING) },
                onSortByDateClick = { onPinnedSortParameterChange(HomeSortParameter.DATE) },
                onSortByTitleClick = { onPinnedSortParameterChange(HomeSortParameter.TITLE) },
                onToggleMenuClick = onTogglePinnedMenuClick
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(state.gridColumns),
                contentPadding = PaddingValues(horizontal = LocalDimen.current.verticalGridHorizontalPadding),
                modifier = Modifier.height(LocalDimen.current.lazyVerticalGridHeight)
            ) {
                items(state.activeTasks) {
                    HomeTaskDetails(
                        title = it.title,
                        description = it.description,
                        pinned = it.isActive,
                        hexColorCode = it.hexColorCode,
                        formatTime = { formatTime(it.createdAt) },
                        formatDate = { formatDate(it.createdAt) },
                        onTaskSelectClick = { onTaskSelectClick(it.id) }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = LocalDimen.current.homeSpacerPaddingTop))
            HomeListBar(
                textId = R.string.list_notes_text,
                expanded = state.showUnpinnedSortDialog,
                selectedHomeSortParameter = state.unpinnedHomeSortParameter,
                selectedSortDirection = state.unpinnedSortDirection,
                onAscendingSortClick = { onUnPinnedDirectionChange(SortDirection.ASCENDING) },
                onDescendingSortClick = { onUnPinnedDirectionChange(SortDirection.DESCENDING) },
                onSortByDateClick = { onUnPinnedSortParameterChange(HomeSortParameter.DATE) },
                onSortByTitleClick = { onUnPinnedSortParameterChange(HomeSortParameter.TITLE) },
                onToggleMenuClick = { onToggleUnPinnedMenuClick() })
            LazyVerticalGrid(
                columns = GridCells.Fixed(state.gridColumns),
                contentPadding = PaddingValues(horizontal = LocalDimen.current.verticalGridHorizontalPadding)
            ) {
                items(state.inActiveTasks) {
                    HomeTaskDetails(
                        title = it.title,
                        description = it.description,
                        pinned = it.isActive,
                        hexColorCode = it.hexColorCode,
                        formatTime = { formatTime(it.createdAt) },
                        formatDate = { formatDate(it.createdAt) },
                        onTaskSelectClick = { onTaskSelectClick(it.id) }
                    )
                }
            }

        }

    }
}

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent(state = HomeState(),
        onNavigationClick = {},
        formatTime = { "" },
        formatDate = { "" },
        onChangeGridColumnsClick = {},
        onTaskSelectClick = {},
        onNavigateToTaskEditorClick = {},
        onPinnedDirectionChange = {},
        onTogglePinnedMenuClick = {},
        onPinnedSortParameterChange = {},
        onToggleUnPinnedMenuClick = {},
        onUnPinnedDirectionChange = {},
        onUnPinnedSortParameterChange = {},
        onChangeNoteFilterType = {})
}