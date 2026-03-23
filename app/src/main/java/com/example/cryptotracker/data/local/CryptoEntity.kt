package com.example.cryptotracker.data.local

import Crypto
import androidx.compose.runtime.currentComposer
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cryptos")
data class CryptoEntity(
    @PrimaryKey()
    val id: String,
    @ColumnInfo("symbol")
    val symbol: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("current_price")
    val currentPrice: Double = 0.0,
    @ColumnInfo("amount")
    val amount: Double = 0.0,
    @ColumnInfo("image_url")
    val imageUrl: String,
    @ColumnInfo("change_24_h")
    val change24h: Double = 0.0
)

fun CryptoEntity.toDomainModel() = Crypto(
    id = this.id,
    symbol = this.symbol,
    name = this.name,
    fiatValue = this.currentPrice.toString(),
    amount = this.amount,
    imgUrl = this.imageUrl,
    marketCapPosition = 0,
    fluctuationValue = 0.0,
    chartTrend = ""
)


fun Crypto.toEntity() = CryptoEntity(
    id = this.id,
    symbol = this.symbol,
    name = this.name,
    currentPrice = this.fiatValue.toDouble(),
    amount = this.amount,
    imageUrl = this.imgUrl,
)