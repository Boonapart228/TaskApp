package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun CategoriesDetails(
    categoriesTitle: String,
    sizeNote: Int,
    onDeleteCategory: () -> Unit,
    onEditCategory: () -> Unit
) {
    Card(
        modifier = Modifier.padding(
            horizontal = LocalDimen.current.categoriesCardHorizontalPadding,
            vertical = LocalDimen.current.categoriesCardVerticalPadding
        ),
        shape = RoundedCornerShape(LocalDimen.current.categoriesCardShape)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            CategoriesDropDawnMenu(
                onDeleteCategory = onDeleteCategory,
                onEditCategory = onEditCategory
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
                    modifier = Modifier.size(LocalDimen.current.categoriesIconSizeLarge)
                )
                Text(
                    text = categoriesTitle, fontSize = LocalDimen.current.categoriesTextSize,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "$sizeNote ${stringResource(id = R.string.all_notes)}",
                        fontSize = LocalDimen.current.categoriesTextSizeSmall,
                        fontWeight = FontWeight.Normal,
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
        onDeleteCategory = {},
        onEditCategory = {}
    )
}