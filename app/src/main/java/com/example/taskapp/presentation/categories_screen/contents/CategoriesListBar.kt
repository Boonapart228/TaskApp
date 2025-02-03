package com.example.taskapp.presentation.categories_screen.contents

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
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun CategoriesListBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalDimen.current.rowCategoriesAllPadding)
            .padding(horizontal = LocalDimen.current.rowCategoriesHorizontalPadding)
    ) {
        Text(
            text = stringResource(id = R.string.categories_list_text),
            fontWeight = FontWeight.Bold,
            fontSize = LocalDimen.current.textCategoriesTitle, modifier = Modifier
                .fillMaxWidth()
                .weight(LocalProperty.current.ninetyPercent)
        )
        Text(
            stringResource(id = R.string.sort_by_text),
            fontSize = LocalDimen.current.textCategoriesSortType,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.padding(horizontal = LocalDimen.current.categoriesSpacerHorizontalPadding))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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
fun CategoriesListBarPreview() {
    CategoriesListBar()
}