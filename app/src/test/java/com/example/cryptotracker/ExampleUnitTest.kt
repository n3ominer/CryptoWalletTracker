package com.example.cryptotracker

import Crypto
import Holding
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_compute_holding_total_value_is_correct() {
        // ARRANGE : Preparer les données de test
        val bitcoin = Crypto(
            id = "1",
            name = "Bitcoin" ,
            symbol = "BTC" ,
            amount = 50000.0 ,
            fiatValue = "50000.00" ,
            chartTrend = "upward" ,
            marketCapPosition = 1 ,
            fluctuationValue = 2.00 ,
            imgUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png" ,
        )
        val holding = Holding(bitcoin, 2.0)

        // ACT: faire quelque chose avec les données
        val holdingValue = holding.totalValue

        // ASSERT: Verifier que le resultat de ACT correspond bien à ce qui devrait arriver
        assertEquals(100000.0, holdingValue, 0.001)
    }
}