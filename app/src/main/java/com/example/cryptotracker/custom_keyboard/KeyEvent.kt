package com.example.cryptotracker.custom_keyboard

sealed class KeyEvent {
    data class Char(val c: kotlin.Char): KeyEvent()
    object Enter: KeyEvent()
    object Space: KeyEvent()
    object Shift: KeyEvent()
    object BackSpace: KeyEvent()

    // Number
    // object Number: keyEvent()
}