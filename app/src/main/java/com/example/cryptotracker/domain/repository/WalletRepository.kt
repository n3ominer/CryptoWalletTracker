package com.example.cryptotracker.domain.repository

import Crypto
import Holding
import Wallet

interface WalletRepository {
    fun getWallet(): Wallet

    /*

    suspend fun getCryptoList(): List<Crypto>
    suspend fun getCryptoDetail(cryptoId: String): Crypto
    suspend fun addHolding(holding: Holding): Wallet
    suspend fun removeHolding(cryptoId: String): Wallet
    suspend fun updateHoldingQuantity(cryptoId: String, quantity: Double): Wallet

     */
}