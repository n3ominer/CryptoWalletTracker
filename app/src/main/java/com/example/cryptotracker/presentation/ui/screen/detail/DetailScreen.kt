package com.example.cryptotracker.presentation.ui.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(
    cryptoName: String
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = cryptoName,
            fontSize = 35.sp,
            color = Color.Black
        )
    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen("Bitcoin")
}