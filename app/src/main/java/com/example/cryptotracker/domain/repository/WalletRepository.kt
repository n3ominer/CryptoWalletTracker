package com.example.cryptotracker.domain.repository

import Crypto

interface WalletRepository {
    fun getCryptos(): List<Crypto>

    // Add Crytpo

}