package com.example.taskapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskapp.presentation.bottom_bar.BottomBar
import com.example.taskapp.presentation.home_screen.contents.HomeTopBar

@Composable
fun HomeContent() {
    Scaffold(topBar = { HomeTopBar() },
        bottomBar = { BottomBar() }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Home")
        }

    }

}