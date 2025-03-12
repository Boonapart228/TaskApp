package com.example.taskapp.presentation.home_screen.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.example.taskapp.presentation.home_screen.model.NotesFilterType
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun HomeNoteSwitcher(
    notesFilterType: NotesFilterType,
    setNoteFilterType: (NotesFilterType) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(LocalDimen.current.rowTopBarPaddingAll)
            .background(Color.Gray, shape = RoundedCornerShape(LocalDimen.current.rowTopBarShape))
            .padding(LocalDimen.current.rowTopBarPaddingAll),
        horizontalArrangement = Arrangement.Center
    ) {
        NotesFilterType.entries.forEach {
            TextButton(
                onClick = { setNoteFilterType(it) },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (notesFilterType == it) MaterialTheme.colorScheme.background else Color.Transparent,
                    contentColor = if (notesFilterType == it) MaterialTheme.colorScheme.primaryContainer else Color.White
                ),
                shape = RoundedCornerShape(LocalDimen.current.rowTopBarShape),
                modifier = Modifier
                    .height(LocalDimen.current.textButtonTopBarHeight)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            if (dragAmount > 0) {
                                setNoteFilterType(NotesFilterType.RECENT)
                            } else if (dragAmount < 0) {
                                setNoteFilterType(NotesFilterType.ALL)
                            }
                        }
                    },
            ) {
                Text(
                    text = stringResource(id = it.textId),
                    fontSize = LocalDimen.current.textTopBar,
                    modifier = Modifier.padding(horizontal = LocalDimen.current.textTopBarHorizontalPadding),
                )
            }
        }
    }
}