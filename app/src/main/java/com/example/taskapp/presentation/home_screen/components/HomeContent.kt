package com.example.taskapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.taskapp.presentation.home_screen.contents.HomeTopBar
import com.example.taskapp.presentation.navigation.model.Screens
import com.example.taskapp.ui.theme.LocalDimen


@Composable
fun HomeContent(
    onNavigationClick: (Screens) -> Unit,
    state: HomeState
) {
    Scaffold(topBar = { HomeTopBar() },
        bottomBar = {
            BottomBar(
                onClick = onNavigationClick,
                selectedNavigate = state.selectedScreen
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigationClick(Screens.TASK_EDITOR_SCREEN) },
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
            HomeListBar(textId = R.string.pinned_notes_text)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = LocalDimen.current.verticalGridHorizontalPadding),
                modifier = Modifier.height(LocalDimen.current.lazyVerticalGridHeight)
            ) {

            }
            Spacer(modifier = Modifier.padding(top = LocalDimen.current.homeSpacerPaddingTop))
            HomeListBar(textId = R.string.list_notes_text)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = LocalDimen.current.verticalGridHorizontalPadding)
            ) {
            }

        }

    }
}

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent({}, HomeState())
}