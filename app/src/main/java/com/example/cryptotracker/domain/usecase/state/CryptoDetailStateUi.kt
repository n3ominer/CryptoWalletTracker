package com.example.cryptotracker.domain.usecase.state

import com.example.cryptotracker.domain.model.CryptoDetail


sealed class CryptoDetailStateUi() {
    data class Success(val crypto: CryptoDetail) : CryptoDetailStateUi()
    data class Error(val message: String): CryptoDetailStateUi()
    data object Loading: CryptoDetailStateUi()

}