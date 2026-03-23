package com.example.cryptotracker.data.remote.dto

import Crypto
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

data class CryptoDto(
    val id: String, // --> ✅
    @SerializedName("current_price")
    val currentPrice: Double, // --> ✅
    val name: String, // --> ✅
    val image: String? = null, // --> ✅
    @SerializedName("market_cap_rank")
    val marketCapRank: Int, // --> ✅
    @SerializedName("price_change_24h")
    val priceChange24h: Double, // --> ✅
    val symbol: String, // --> ✅,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)

fun CryptoDto.toDomainModel(): Crypto =
     Crypto(
         id = this.id,
         name = this.name,
         fiatValue = this.currentPrice.toString(),
         symbol = this.symbol,
         imgUrl = this.image ?: "",
         fluctuationValue = this.priceChange24h,
         marketCapPosition = this.marketCapRank,
         amount = 0.0,
         chartTrend = if(this.priceChangePercentage24h < 0) "down" else "up",
         iconColor = Color.Transparent,
     )
