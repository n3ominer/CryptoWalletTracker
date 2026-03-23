package com.example.cryptotracker.data.remote.dto.detail

import com.example.cryptotracker.domain.model.CryptoDetail
import com.google.gson.annotations.SerializedName
import kotlin.Int

data class CryptoDetailDto(
    val eur: Int,
    @SerializedName("eur_24h_change")
    val eur24hChange: Double,
    @SerializedName("eur_24h_vol")
    val eur24hVol: Double,
    @SerializedName("eur_market_cap")
    val eurMarketCap: Double
)


fun CryptoDetailDto.toDomainModel() = CryptoDetail(
        eur = this.eur,
        eur24hChange = this.eur24hChange,
        eur24hVol = this.eur24hVol,
        eurMarketCap = this.eurMarketCap
    )