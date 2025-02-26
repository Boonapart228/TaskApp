package com.example.taskapp.presentation.categories_screen.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.ui.theme.AppTheme
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun CategoriesAddButton(
    onToggleDialogClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalDimen.current.cardHorizontalPadding)
            .padding(vertical = LocalDimen.current.cardVerticalPadding)
            .clickable { onToggleDialogClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = LocalDimen.current.cardElevation
        ),
        shape = RoundedCornerShape(LocalDimen.current.cardShape),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(onClick = { onToggleDialogClick() }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(LocalDimen.current.iconMediumSize)
                )
            }
            Text(
                text = stringResource(id = R.string.create_categories_text),
                fontWeight = FontWeight.Medium,
                fontSize = LocalDimen.current.textCategoriesTitleMediumSize
            )
        }
    }
}

@Composable
@Preview(showSystemUi = false)
fun CategoriesAddButtonPreview() {
    AppTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CategoriesAddButton({})
    }
}