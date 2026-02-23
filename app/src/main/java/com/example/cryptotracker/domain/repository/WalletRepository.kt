package com.example.cryptotracker.domain.repository

import Crypto
import Wallet

interface WalletRepository {
    fun getCryptos(): Wallet

    // Add Crytpo

}