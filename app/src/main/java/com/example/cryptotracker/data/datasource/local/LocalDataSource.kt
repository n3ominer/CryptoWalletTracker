package com.example.cryptotracker.data.datasource.local

import Crypto
import Holding
import Wallet
import androidx.compose.ui.graphics.Color

class LocalDataSource {

    private var wallet = Wallet(
        id = 1,
        name = "Wallet 1",
        holdings = listOf(
            Holding(
                Crypto(
                    name = "Bitcoin",
                    symbol = "b1",
                    amount = 2.21,
                    fiatValue = "65853,44",
                    chartTrend = "down",
                    //iconColor = Color.Yellow
                ),
                2.0
            ),
            Holding(
                Crypto(
                    name = "Ethereum",
                    symbol = "e1",
                    amount = 1.5,
                    fiatValue = "2000,44",
                    chartTrend = "down",
                    //iconColor = Color.Yellow
                ),
                2.0)
            )
    )



    fun getWallet(): Wallet {
        return wallet
    }

    fun getCryptoList(): List<Crypto> {
        return wallet.holdings.map { it.crypto }
    }

    fun addHolding(holding: Holding) {
        wallet = wallet.copy(
            holdings = wallet.holdings + holding
        )
    }
}