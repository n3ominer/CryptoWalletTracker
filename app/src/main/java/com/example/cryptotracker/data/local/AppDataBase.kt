package com.example.cryptotracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [CryptoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

    companion object {

        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "database-name"
            ).build()
        }
    }
}