package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.domain.repository.WalletRepository
import com.example.cryptotracker.domain.usecase.state.WalletStateUi

class GetWalletCryptosUseCase (val repository: WalletRepository) {

    suspend operator fun invoke(): WalletStateUi {
        if (repository.getWallet().holdings.isEmpty()) {
            return WalletStateUi.Error(message = "Pas de cryptos dans le wallet")
        } else {
            return WalletStateUi.Success(repository.getWallet())
        }
    }

}