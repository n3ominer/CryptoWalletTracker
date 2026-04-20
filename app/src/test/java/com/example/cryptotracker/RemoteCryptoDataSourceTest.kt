package com.example.cryptotracker

import CoinGeckoTokensService
import RemoteCryptoDataSource
import com.example.cryptotracker.data.local.CryptoDao
import com.example.cryptotracker.data.remote.dto.crypto.CryptoDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RemoteCryptoDataSourceTest {

    // API Service
    private lateinit var mockApiService: CoinGeckoTokensService
    // DataSource
    private lateinit var dataSource: RemoteCryptoDataSource
    // DAO
    private lateinit var mockDao: CryptoDao

    // Before --> Setup
    @Before
    fun setup() {
        mockDao = mock() // --> Mock instance de DAO
        mockApiService = mock(CoinGeckoTokensService::class.java)
        dataSource = RemoteCryptoDataSource(
            apiService = mockApiService,
            cryptoDao =  mockDao
        )
    }


    // test appel HTTP via api service
    @Test
    fun test_getCryptos_returnsSuccess() = runBlocking {
        // ARRANGE
        val mockDtos = listOf(
            CryptoDto(
                id = "bitcoin",
                currentPrice = 50000.0,
                name = "Bitcoin",
                image = "https://cryptologos.cc/logos/bitcoin-btc-logo.png",
                marketCapRank = 1,
                priceChange24h = 1000.0,
                symbol = "BTC",
                priceChangePercentage24h = 2.0
            ),
            CryptoDto(
                id = "ethereum",
                currentPrice = 4000.0,
                name = "Ethereum",
                image = "https://cryptologos.cc/logos/ethereum-eth-logo.png",
                marketCapRank = 2,
                priceChange24h = 200.0,
                symbol = "ETH",
                priceChangePercentage24h = 5.0
            )
        )

        `when`(mockApiService.getCryptos())
            .thenReturn(mockDtos)
        // ACT
        val result = dataSource.getCryptos()
        // RemoteCryptoDataSource -> getCryptos() --> CoinGeckoTokensService() -> getCryptos()

        //ASSERT
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.size)
    }


}