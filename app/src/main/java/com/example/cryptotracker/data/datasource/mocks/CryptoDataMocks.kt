package com.example.cryptotracker.data.datasource.mocks

import Crypto
import androidx.compose.ui.graphics.Color


fun getCryptoList(): List<Crypto> {
    return listOf(
        Crypto(
            name = "Bitcoin",
            symbol = "b1",
            amount = 2.21,
            fiatValue = "65853,44",
            chartTrend = "down",
            iconColor = Color.Yellow
        ),
        Crypto(
            name = "Ethereum",
            symbol = "B2",
            amount = 2.21,
            fiatValue = "2000",
            chartTrend = "down",
            iconColor = Color.Blue
        )
    )
}