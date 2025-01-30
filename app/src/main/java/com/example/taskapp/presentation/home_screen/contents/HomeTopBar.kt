package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.ui.theme.AppTheme
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

@Composable
fun HomeTopBar() {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(
                LocalDimen.current.columnTopBarPadding,
                top = LocalDimen.current.columnTopBarPaddingTop,
                bottom = LocalDimen.current.columnTopBarPaddingBottom
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(
                top = LocalDimen.current.rowTopBarPaddingTop,
                start = LocalDimen.current.rowTopBarPaddingStart
            )
        ) {
            Text(
                text = stringResource(id = R.string.home_title),
                modifier = Modifier.weight(LocalProperty.current.eightyPercent),
                style = TextStyle(
                    fontSize = LocalDimen.current.textTopBarTitle,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.background
                )
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(LocalProperty.current.tenPercent)
            ) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(LocalProperty.current.tenPercent)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SegmentedControl()
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.offset(x = LocalDimen.current.iconButtonTopBarOffsetByX)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_grid_view_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
fun SegmentedControl() {
    var isSelected by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(LocalDimen.current.rowTopBarPaddingAll)
            .background(Color.Gray, shape = RoundedCornerShape(LocalDimen.current.rowTopBarShape))
            .padding(LocalDimen.current.rowTopBarPaddingAll),
        horizontalArrangement = Arrangement.Center
    ) {
        listOf(true to R.string.all_notes, false to R.string.resent).forEach { (state, textId) ->
            TextButton(
                onClick = { isSelected = state },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isSelected == state) MaterialTheme.colorScheme.background else Color.Transparent,
                    contentColor = if (isSelected == state) MaterialTheme.colorScheme.primaryContainer else Color.White
                ),
                shape = RoundedCornerShape(LocalDimen.current.rowTopBarShape),
                modifier = Modifier
                    .height(LocalDimen.current.textButtonTopBarHeight),
            ) {
                Text(
                    text = stringResource(id = textId),
                    fontSize = LocalDimen.current.textTopBar,
                    modifier = Modifier.padding(horizontal = LocalDimen.current.textTopBarHorizontalPadding),
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeTopBarPreview() {
    AppTheme(dynamicColor = false) {
        HomeTopBar()
    }
}