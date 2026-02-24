package com.example.cryptotracker.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("wallet")
    object Detail: Screen("detail")

}