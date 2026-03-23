package com.example.cryptotracker.domain.repository

import Crypto
import Holding
import Wallet
import com.example.cryptotracker.domain.model.CryptoDetail

interface WalletRepository {
    suspend fun getWallet(): Wallet

    suspend fun getCryptoDetail(cryptoId: String): CryptoDetail?

    /*
    suspend fun getCryptoList(): List<Crypto>
    suspend fun addHolding(holding: Holding): Wallet
    suspend fun removeHolding(cryptoId: String): Wallet
    suspend fun updateHoldingQuantity(cryptoId: String, quantity: Double): Wallet
     */
}