package com.example.taskapp.presentation.home_screen.contents

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty


@Composable
fun HomeTaskDetails(
    title: String,
    description: String,
    pinned: Boolean,
    allTitleLines: Boolean,
    hexColorCode: String,
    formatDate: () -> String,
    formatTime: () -> String,
    onTaskSelectClick: () -> Unit,
    onToggleAllTitleLines: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        Card(
            modifier = Modifier
                .padding(
                    horizontal = LocalDimen.current.homeDetailsCardHorizontalPadding,
                )
                .padding(
                    bottom = LocalDimen.current.homeDetailsCardVerticalPadding,
                    top = if (pinned) LocalDimen.current.homeDetailsPinnedCardVerticalPadding else LocalDimen.current.homeDetailsUnpinnedCardVerticalPadding
                )
                .clickable { onTaskSelectClick() },
            colors = CardDefaults.cardColors(containerColor = Color(parseColor(hexColorCode))),
            shape = RoundedCornerShape(LocalDimen.current.categoriesCardShape)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.categoriesColumnSpaceBy),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = LocalDimen.current.categoriesColumnPaddingVertical,
                        horizontal = LocalDimen.current.categoriesColumnPaddingHorizontal
                    )
            ) {
                Text(
                    text = title, fontSize = LocalDimen.current.categoriesTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    maxLines = if (allTitleLines) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggleAllTitleLines() },
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = description,
                        fontSize = LocalDimen.current.categoriesTextSizeSmall,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        maxLines = 3,
                        minLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = LocalDimen.current.categoriesTextBottomPadding)
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(LocalDimen.current.homeDetailsHorizontalSpacedBy),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.time_ui),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(LocalDimen.current.homeDetailsIconSize)
                        )
                        Text(
                            text = formatTime(), textAlign = TextAlign.Center,
                            fontSize = LocalDimen.current.homeDetailsDataTextSize,
                            color = Color.Black
                        )
                        Text(
                            text = "|", textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Icon(
                            imageVector = Icons.Default.DateRange, contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(LocalDimen.current.homeDetailsIconSize)
                        )
                        Text(
                            text = formatDate(),
                            textAlign = TextAlign.Center,
                            fontSize = LocalDimen.current.homeDetailsDataTextSize,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        if (pinned) {
            Icon(
                painter = painterResource(id = R.drawable.pin_notes_home_ui),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .size(LocalDimen.current.homePinIconSize)
                    .offset(
                        y = LocalProperty.current.offSetByY.dp,
                        x = LocalProperty.current.offSetByX.dp
                    )
            )
        }
    }
}

@Composable
@Preview
fun HomeTaskDetailsPreview() {
    HomeTaskDetails(
        "Title",
        "You said you'll be with me forever\\nWith you personally, I am absolutely sincere.",
        pinned = true,
        allTitleLines = false,
        hexColorCode = "#dfaf7e",
        formatDate = { "" },
        formatTime = { "" },
        onTaskSelectClick = {},
        onToggleAllTitleLines = {}
    )
}