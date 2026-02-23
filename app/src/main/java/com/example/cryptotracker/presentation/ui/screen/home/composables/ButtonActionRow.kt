package com.example.cryptotracker.presentation.ui.screen.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.R

@Composable
fun ButtonActionRow() {
    Row(
        modifier = Modifier.fillMaxWidth()
        .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HomeQuickActionbutton(R.drawable.outline_call_receive_crypto_24, "Receive")
        HomeQuickActionbutton(R.drawable.outline_asend_crypto_24, "Send")
        HomeQuickActionbutton(R.drawable.outline_swap_crypto_24, "Swap")
        HomeQuickActionbutton(R.drawable.outline_buy_crypto_24, "Buy")
    }
}


@Preview
@Composable
fun ButtonActionRowPreview() {
    ButtonActionRow()
}