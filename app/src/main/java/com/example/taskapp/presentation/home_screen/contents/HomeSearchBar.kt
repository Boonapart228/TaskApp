package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun HomeSearchBar(
    searchTitle: String,
    modifier: Modifier = Modifier,
    onSetSearchTitle: (String) -> Unit,
    onToggleSearchBar: () -> Unit,
    onNavigateToSettingScreen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(
            top = LocalDimen.current.rowTopBarPaddingTop,
            start = LocalDimen.current.rowTopBarPaddingStart
        )
    ) {
        BasicTextField(
            value = searchTitle,
            onValueChange = onSetSearchTitle,
            modifier = modifier
                .height(LocalDimen.current.basicTextFieldHeight)
                .fillMaxWidth()
                .border(
                    LocalDimen.current.basicTextFieldBorderWidth,
                    MaterialTheme.colorScheme.background,
                    CircleShape
                )
                .padding(
                    horizontal = LocalDimen.current.basicTextFieldHorizontalPadding,
                    vertical = LocalDimen.current.basicTextFieldVerticalPadding
                )
                .weight(LocalProperty.current.ninetyPercent),
            textStyle = TextStyle(
                fontSize = LocalDimen.current.basicTextFieldFontSize,
                color = MaterialTheme.colorScheme.onSurface
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = LocalDimen.current.searchBarRowHorizontalPadding)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                    Spacer(modifier = Modifier.width(LocalDimen.current.searchBarSpacerWidth))
                    Box(modifier = Modifier.weight(LocalProperty.current.tenPercent)) {
                        if (searchTitle.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.search_text),
                                fontSize = LocalDimen.current.basicTextFieldPlaceHolderFontSize,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(LocalDimen.current.searchBarSpacerWidth))
                    IconButton(
                        onClick = onToggleSearchBar,
                        modifier = Modifier.offset(x = LocalProperty.current.offSetByXSearchBarIconButton.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }

                }
            }
        )
        IconButton(
            onClick = onNavigateToSettingScreen,
            modifier = Modifier.weight(LocalProperty.current.tenPercent)
        ) {
            Icon(
                imageVector = Icons.Default.Settings, contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeSearchBarPreview() {

    HomeSearchBar(
        searchTitle = "",
        onToggleSearchBar = {},
        onSetSearchTitle = {},
        onNavigateToSettingScreen = {})

}