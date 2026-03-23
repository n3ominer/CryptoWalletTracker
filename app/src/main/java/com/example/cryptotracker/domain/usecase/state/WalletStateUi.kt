package com.example.cryptotracker.domain.usecase.state

import Wallet

sealed class WalletStateUi() {
    data class Success(val crypto: Wallet) : WalletStateUi()
    data class Error(val message: String): WalletStateUi()
    data object Loading: WalletStateUi()

}