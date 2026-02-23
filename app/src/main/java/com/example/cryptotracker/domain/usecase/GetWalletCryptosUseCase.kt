package com.example.cryptotracker.domain.usecase

import Crypto
import Holding
import Wallet
import WalletRepositoryImpl
import com.example.cryptotracker.domain.repository.WalletRepository

class GetWalletCryptosUseCase (val repository: WalletRepository) {

    /*suspend*/ operator fun invoke(): List<Crypto>{

        return repository.getCryptos()
    }

}