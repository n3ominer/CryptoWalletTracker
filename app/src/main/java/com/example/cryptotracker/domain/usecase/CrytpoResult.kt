package com.example.cryptotracker.domain.usecase

import Wallet

sealed class CrytpoResult() {
    data class Success(val crypto: Wallet) : CrytpoResult()
    data class Error(val message: String): CrytpoResult()
    data object Loading: CrytpoResult()

}