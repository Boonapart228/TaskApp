package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.presentation.home_screen.model.SortParameter
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun HomeListBar(
    textId: Int,
    expanded: Boolean,
    selectedSortParameter: SortParameter,
    onAscendingSortClick: () -> Unit,
    onDescendingSortClick: () -> Unit,
    onSortByTitleClick: () -> Unit,
    onSortByDateClick: () -> Unit,
    onToggleMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimen.current.rowCategoriesAllPadding)
            .padding(horizontal = LocalDimen.current.rowCategoriesHorizontalPadding)
    ) {
        Text(
            text = stringResource(id = textId),
            fontWeight = FontWeight.ExtraBold,
            fontSize = LocalDimen.current.homeTextCategoryTitle, modifier = Modifier
                .fillMaxWidth()
                .weight(LocalProperty.current.ninetyPercent)
        )
        HomeDropDawnMenu(
            onToggleMenuClick = onToggleMenuClick,
            onSortByTitleClick = onSortByTitleClick,
            onSortByDateClick = onSortByDateClick,
            selectedSortParameter = selectedSortParameter,
            expanded = expanded
        )
        Spacer(modifier = Modifier.padding(horizontal = LocalDimen.current.categoriesSpacerHorizontalPadding))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = onAscendingSortClick,
                modifier = Modifier.size(LocalDimen.current.categoriesIconButtonSize)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.categoriesIconSize)
                        .graphicsLayer(
                            rotationZ = LocalProperty.current.rotation270
                        )
                        .offset(x = (-LocalDimen.current.categoriesIconOffset))
                )
            }
            IconButton(
                onClick = onDescendingSortClick,
                modifier = Modifier.size(LocalDimen.current.categoriesIconButtonSize)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .size(LocalDimen.current.categoriesIconSize)
                        .graphicsLayer(
                            rotationZ = LocalProperty.current.rotation90
                        )
                        .offset(x = (-LocalDimen.current.categoriesIconOffset))
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeListBarBarPreview() {
    HomeListBar(R.string.list_notes_text,
        expanded = false,
        selectedSortParameter = SortParameter.DATE,
        onDescendingSortClick = {},
        onAscendingSortClick = {},
        onSortByDateClick = {},
        onSortByTitleClick = {},
        onToggleMenuClick = {})
}