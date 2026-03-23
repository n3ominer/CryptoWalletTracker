package com.example.cryptotracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CryptoDao {
    @Query("SELECT * FROM cryptos")
    suspend fun getAllCryptos(): List<CryptoEntity>

    @Insert
    suspend fun insertAll(cryptos: List<CryptoEntity>)
}