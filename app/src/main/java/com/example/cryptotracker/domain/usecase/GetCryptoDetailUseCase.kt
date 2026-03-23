package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.domain.repository.WalletRepository
import com.example.cryptotracker.domain.usecase.state.CryptoDetailStateUi
import com.example.cryptotracker.domain.usecase.state.WalletStateUi

class GetCryptoDetailUseCase (val repository: WalletRepository) {

    suspend operator fun invoke(id: String): CryptoDetailStateUi {
        val detail = repository.getCryptoDetail(id)
        detail?.let {
            return CryptoDetailStateUi.Success(it)
        }

        return CryptoDetailStateUi.Error(message = "Pas de cryptos dans le wallet")
    }

}