package com.example.taskapp.presentation.categories_screen.contents

import android.graphics.Color.parseColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun CategoriesDetails(
    categoriesTitle: String,
    sizeNote: Int,
    hexColorCode: String,
    allLines: Boolean,
    onDeleteCategory: () -> Unit,
    onEditCategory: () -> Unit,
    onCategorySelectClick: () -> Unit,
    onToggleAllLines: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = LocalDimen.current.categoriesCardHorizontalPadding,
            )
            .padding(bottom = LocalDimen.current.categoriesDetailsPaddingBottom)
            .clickable { onCategorySelectClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(parseColor(hexColorCode))
        ),
        shape = RoundedCornerShape(LocalDimen.current.categoriesCardShape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalDimen.current.categoriesBoxPaddingTop),
            contentAlignment = Alignment.TopEnd,
        ) {
            CategoriesDropDawnMenu(
                onDeleteCategory = onDeleteCategory,
                onEditCategory = onEditCategory,
                modifier = Modifier.offset(y = LocalProperty.current.offSetByYCategoriesDropDawnMenu.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.categoriesColumnSpaceBy),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalDimen.current.categoriesColumnPaddingAll)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.file_ui),
                    contentDescription = null,
                    modifier = Modifier.size(LocalDimen.current.categoriesIconSizeLarge),
                    tint = Color.Black
                )
                Text(
                    text = categoriesTitle, fontSize = LocalDimen.current.categoriesTextSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = if (allLines) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable { onToggleAllLines() }
                        .padding(horizontal = LocalDimen.current.categoriesDetailsPaddingHorizontal)
                )
                Row {
                    Text(
                        text = "$sizeNote ${stringResource(id = R.string.all_notes)}",
                        fontSize = LocalDimen.current.categoriesTextSizeSmall,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = LocalDimen.current.categoriesTextBottomPadding)
                    )
                }

            }
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun CategoriesDetailsPreview() {
    CategoriesDetails(
        categoriesTitle = "Category 01",
        sizeNote = 2,
        allLines = false,
        onDeleteCategory = {},
        onEditCategory = {},
        onCategorySelectClick = {},
        hexColorCode = "#dda5e4",
        onToggleAllLines = {}
    )
}