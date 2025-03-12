package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.domain.constants.SortDirection
import com.example.taskapp.presentation.categories_screen.model.CategorySortParameter
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty
import com.example.taskapp.ui.theme.pastelGreen
import com.example.taskapp.ui.theme.pastelRed

@Composable
fun CategoriesListBar(
    textId: Int,
    expanded: Boolean,
    selectedSortParameter: CategorySortParameter,
    selectedSortDirection: SortDirection,
    onAscendingSortClick: () -> Unit,
    onDescendingSortClick: () -> Unit,
    onSortByTitleClick: () -> Unit,
    onSortByNoteCountClick: () -> Unit,
    onToggleMenuClick: () -> Unit
) {
    val animationAscendingColorState = animateColorAsState(
        targetValue = if (selectedSortDirection == SortDirection.ASCENDING) pastelGreen else pastelRed,
        tween(LocalProperty.current.colorAnimationDurationMs),
        label = ""
    )
    val animationDescendingColorState = animateColorAsState(
        targetValue = if (selectedSortDirection == SortDirection.DESCENDING) pastelGreen else pastelRed,
        tween(LocalProperty.current.colorAnimationDurationMs),
        label = ""
    )
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
        CategoryDropDawnMenu(
            onToggleMenuClick = onToggleMenuClick,
            onSortByTitleClick = onSortByTitleClick,
            onSortByNoteCountClick = onSortByNoteCountClick,
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
                modifier = Modifier
                    .size(LocalDimen.current.iconSortButtonSize)
                    .offset(y = LocalProperty.current.offSetByYAscendingIconButton.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = animationAscendingColorState.value,
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = LocalProperty.current.rotation270
                        )

                )
            }
            IconButton(
                onClick = onDescendingSortClick,
                modifier = Modifier
                    .size(LocalDimen.current.iconSortButtonSize)
                    .offset(y = LocalProperty.current.offSetByYCategoryDescendingIconButton.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = animationDescendingColorState.value,
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = LocalProperty.current.rotation90
                        )
                        .offset(x = LocalProperty.current.offSetByXDescendingIcon.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoriesListBarPreview() {
    CategoriesListBar(
        textId = R.string.categories_list_text,
        expanded = false,
        selectedSortParameter = CategorySortParameter.TITLE,
        selectedSortDirection = SortDirection.DESCENDING,
        onSortByNoteCountClick = {},
        onSortByTitleClick = {},
        onDescendingSortClick = {},
        onAscendingSortClick = {},
        onToggleMenuClick = {},

        )
}