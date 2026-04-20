package com.example.cryptotracker.fake_repo

import Crypto
import Holding
import Wallet
import com.example.cryptotracker.domain.model.CryptoDetail
import com.example.cryptotracker.domain.repository.WalletRepository

class FakeWalletRepository: WalletRepository {

    private var wallet = Wallet(
        name = "Test Wallet",
        holdings = listOf(
            Holding(
                crypto = Crypto (
                    id = "bitcoin",
                    name = "Bitcoin",
                    symbol = "BTC",
                    amount = 1.0,
                    fiatValue = "50000.00",
                    chartTrend = "upward",
                    marketCapPosition = 1,
                    fluctuationValue = 2.00,
                    imgUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png"
                ),
                quantity = 1.0
            )
        )
    )

    private var shouldFail = false
    private var failureMessage = "Test error"

    fun setWallet(newWallet: Wallet) {
        wallet = newWallet
    }

    fun setShouldFail(fail: Boolean, message: String = "Test error") {
        shouldFail = fail
        failureMessage = message
    }

    override suspend fun getWallet(): Wallet {
        if (shouldFail) { throw Exception(failureMessage) }
        return wallet
    }

    override suspend fun getCryptoDetail(cryptoId: String): CryptoDetail? {
        if (shouldFail) { throw Exception(failureMessage) }
        return CryptoDetail(
            eur = 500000,
            12.0,
            1000.0,
            100000.0
        )
    }
}