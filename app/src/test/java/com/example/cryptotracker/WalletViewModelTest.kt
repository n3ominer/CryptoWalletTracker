package com.example.cryptotracker

import Crypto
import Holding
import Wallet
import WalletViewModel
import com.example.cryptotracker.domain.repository.WalletRepository
import com.example.cryptotracker.domain.usecase.GetCryptoDetailUseCase
import com.example.cryptotracker.domain.usecase.GetWalletCryptosUseCase
import com.example.cryptotracker.domain.usecase.state.WalletStateUi
import com.example.cryptotracker.fake_repo.FakeWalletRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WalletViewModelTest {

    private lateinit var fakeRepository: FakeWalletRepository
    private lateinit var viewModel: WalletViewModel

    @Before
    fun setup() {
        // Repository fake
        fakeRepository = FakeWalletRepository()
        // Creer le use case GetWalletCryptosUseCase
        val getWalletCryptosUseCase = GetWalletCryptosUseCase(fakeRepository)
        // Creer le use case GetCryptoDetailUseCase
        val getCryptoDetailUseCase = GetCryptoDetailUseCase(fakeRepository)

        // On instancie le VM
        viewModel = WalletViewModel(
            getWalletCryptosUseCase,
            getCryptoDetailUseCase
        )

    }

    @Test
    fun test_loadWallet_setsSuccessState() {
        // ARRANGE
        val testWallet = Wallet(
            name = "Test",
            holdings = listOf(
                Holding(
                    Crypto(
                        id = "1",
                        name = "Bitcoin" ,
                        symbol = "BTC" ,
                        amount = 50000.0 ,
                        fiatValue = "50000.00" ,
                        chartTrend = "upward" ,
                        marketCapPosition = 1 ,
                        fluctuationValue = 2.00 ,
                        imgUrl = "https://cryptologos.cc/logos/bitcoin-btc-logo.png" ,
                    ),
                    1.0
                )
            )
        )
        fakeRepository.setWallet(testWallet)

        // ACT
        viewModel.loadWallet()

        runBlocking {
            delay(1000)
        }
        // ASSERT
        val state = viewModel.walletUiState.value
        assertTrue(state is WalletStateUi.Success)
        assertEquals(
            testWallet,
            (state as WalletStateUi.Success).crypto
        )
    }

    @Test
    fun test_loadWallet_setsErrorState_onFailure() {
        // ARRANGE
        val errMessage = "Network ERR"
        fakeRepository.setShouldFail(true, errMessage)

        // ACT
        viewModel.loadWallet()
        runBlocking { delay(1000) }

        // ASSERT
        val state = viewModel.walletUiState.value
        assertTrue(state is WalletStateUi.Error)
        assertEquals(
            errMessage,
            (state as WalletStateUi.Error).message
        )
    }
}