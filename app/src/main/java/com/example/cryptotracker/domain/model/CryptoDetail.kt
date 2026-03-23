package com.example.cryptotracker.domain.model

import com.google.gson.annotations.SerializedName

data class CryptoDetail (
    val eur: Int,
    val eur24hChange: Double,
    val eur24hVol: Double,
    val eurMarketCap: Double
)