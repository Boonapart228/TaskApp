package com.example.taskapp.presentation.home_screen.contents

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    modifier: Modifier = Modifier,
    formatDate: () -> String,
    formatTime: () -> String
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        Card(
            modifier = Modifier.padding(
                horizontal = LocalDimen.current.homeDetailsCardHorizontalPadding,
                vertical = LocalDimen.current.homeDetailsCardVerticalPadding
            ),
            shape = RoundedCornerShape(LocalDimen.current.categoriesCardShape)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.categoriesColumnSpaceBy),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalDimen.current.categoriesColumnPaddingAll)
            ) {
                Text(
                    text = title, fontSize = LocalDimen.current.categoriesTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = description,
                        fontSize = LocalDimen.current.categoriesTextSizeSmall,
                        fontWeight = FontWeight.Normal,
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
                            Modifier.size(LocalDimen.current.homeDetailsIconSize)
                        )
                        Text(
                            text = formatTime(), textAlign = TextAlign.Center,
                            fontSize = LocalDimen.current.homeDetailsDataTextSize
                        )
                        Text(text = "|", textAlign = TextAlign.Center)
                        Icon(
                            imageVector = Icons.Default.DateRange, contentDescription = null,
                            Modifier.size(LocalDimen.current.homeDetailsIconSize)
                        )
                        Text(
                            text = formatDate(),
                            textAlign = TextAlign.Center,
                            fontSize = LocalDimen.current.homeDetailsDataTextSize
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
        formatDate = { "" },
        formatTime = { "" }
    )
}