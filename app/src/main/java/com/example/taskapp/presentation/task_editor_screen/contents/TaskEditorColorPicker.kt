package com.example.taskapp.presentation.task_editor_screen.contents

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskapp.R
import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.ui.theme.LocalDimen

@Composable
fun TaskEditorColorPicker(
    hexColorCode: String,
    onSelectColorClick: (String) -> Unit,
    onToggleColorPicker: () -> Unit,
    onSaveColorClick: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.palette_ui), contentDescription = null,
                tint = Color(parseColor(hexColorCode))
            )
        },
        title = {
            Text(text = stringResource(id = R.string.choose_color_text))
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ColorItems.entries.forEach {
                    Button(
                        onClick = { onSelectColorClick(it.hexColorCode) }, shape = CircleShape,
                        modifier = Modifier.size(LocalDimen.current.colorButtonSize),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(parseColor(it.hexColorCode))
                        )
                    ) {
                    }
                    Spacer(modifier = Modifier.width(LocalDimen.current.colorButtonWidth))
                }
            }
        },
        onDismissRequest = {
            onToggleColorPicker()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onToggleColorPicker()
                    onSaveColorClick()
                }
            ) {
                Text(stringResource(id = R.string.save_text))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onToggleColorPicker()
                }
            ) {
                Text(stringResource(id = R.string.dismiss_text))
            }
        }
    )
}


@Composable
@Preview
fun TaskEditorColorPickerPreview() {
    TaskEditorColorPicker(
        hexColorCode = "",
        onSelectColorClick = {},
        onToggleColorPicker = {},
        onSaveColorClick = {}
    )
}