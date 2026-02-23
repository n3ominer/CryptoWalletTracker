package com.example.cryptotracker.data.datasource.mocks

import Crypto
import androidx.compose.ui.graphics.Color


fun getCryptoList(): List<Crypto> {
    return listOf(
        Crypto(
            name = "Bitcoin",
            symbol = "",
            amount = 2.21,
            fiatValue = "65853,44",
            chartTrend = "down",
            iconColor = Color(0xFEA01B)
        ),
        Crypto(
            name = "Bitcoin",
            symbol = "",
            amount = 2.21,
            fiatValue = "65853,44",
            chartTrend = "down",
            iconColor = Color(0xFEA01B)
        )
    )
}