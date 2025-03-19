package com.example.taskapp.presentation.task_editor_screen.contents

import android.graphics.Color.parseColor
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.unit.dp
import com.example.taskapp.R
import com.example.taskapp.domain.constants.ColorItems
import com.example.taskapp.ui.theme.LocalDimen
import com.example.taskapp.ui.theme.LocalProperty

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
                    Box {
                        Button(
                            onClick = { onSelectColorClick(it.hexColorCode) }, shape = CircleShape,
                            modifier = Modifier.size(LocalDimen.current.colorButtonSize),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(parseColor(it.hexColorCode))
                            )
                        ) {
                        }
                        androidx.compose.animation.AnimatedVisibility(
                            visible = it.hexColorCode == hexColorCode, enter = scaleIn(),
                            exit = scaleOut()
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.offset(
                                    x = LocalProperty.current.offSetByXSelectedColorIcon.dp,
                                    y = LocalProperty.current.offSetByYSelectedColorIcon.dp
                                ),
                                tint = Color.Black
                            )
                        }
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