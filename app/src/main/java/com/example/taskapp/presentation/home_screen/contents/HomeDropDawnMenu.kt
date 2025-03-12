package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.presentation.home_screen.model.HomeSortParameter
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun HomeDropDawnMenu(
    onToggleMenuClick: () -> Unit,
    onSortByTitleClick: () -> Unit,
    onSortByDateClick: () -> Unit,
    expanded: Boolean,
    selectedHomeSortParameter: HomeSortParameter
) {
    Box(
        modifier = Modifier
            .height(LocalDimen.current.homeBoxHeight)
            .offset(x = LocalProperty.current.offSetByXRow.dp)
    ) {
        TextButton(
            onClick = onToggleMenuClick,
            modifier = Modifier.offset(
                y = LocalProperty.current.offSetByYTextButton.dp,
                x = LocalProperty.current.offSetByXTextButton.dp
            )
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.sort_by_text),
                    fontSize = LocalDimen.current.textCategoriesSortType,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Icon(
                    painter = when (selectedHomeSortParameter) {
                        HomeSortParameter.TITLE -> {
                            painterResource(id = R.drawable.sort_by_title_ui)
                        }

                        HomeSortParameter.DATE -> {
                            painterResource(id = R.drawable.sort_by_date_ui)
                        }
                    },
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(LocalDimen.current.selectedIconSize)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onToggleMenuClick
        ) {
            HomeSortParameter.entries.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = item.textId),
                            modifier = Modifier.weight(LocalProperty.current.tenPercent)
                        )
                    },
                    leadingIcon = {
                        Row {
                            Box(modifier = Modifier.size(LocalDimen.current.homeBoxSize)) {
                                if (selectedHomeSortParameter == item) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(LocalDimen.current.homeSpacerWidth))
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = null
                            )
                        }
                    },
                    onClick = {
                        when (item) {
                            HomeSortParameter.TITLE -> {
                                onSortByTitleClick()
                            }

                            HomeSortParameter.DATE -> {
                                onSortByDateClick()
                            }
                        }
                        onToggleMenuClick()
                    }
                )
            }
        }
    }
}


@Composable
@Preview
fun HomeDropDawnMenuPreview() {
    HomeDropDawnMenu(
        expanded = false,
        selectedHomeSortParameter = HomeSortParameter.DATE,
        onToggleMenuClick = {},
        onSortByDateClick = {},
        onSortByTitleClick = {}
    )
}
