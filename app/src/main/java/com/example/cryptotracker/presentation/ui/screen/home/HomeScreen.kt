package com.example.cryptotracker.presentation.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptotracker.presentation.ui.screen.home.composables.ButtonActionRow
import com.example.cryptotracker.presentation.ui.screen.home.composables.WalletHomeHeader

@Composable
fun HomeScreen() {
    Column {
        WalletHomeHeader()
        ButtonActionRow()
    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8E24AA), // Violet foncé
                        Color(0xFFBA68C8)  // Violet clair
                    )
                )
            ),
        color = Color.Transparent
    ) {
        HomeScreen()
    }
}