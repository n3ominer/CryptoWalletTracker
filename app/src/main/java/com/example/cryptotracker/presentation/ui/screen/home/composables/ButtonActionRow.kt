package com.example.cryptotracker.presentation.ui.screen.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.R

@Composable
fun ButtonActionRow() {
    val receiveButtonText = stringResource(R.string.home_header_row_button_receive)
    val sendButtonText = stringResource(R.string.home_header_row_button_receive)
    val swapButtonText = stringResource(R.string.home_header_row_button_receive)
    val buyButtonText = stringResource(R.string.home_header_row_button_receive)

    Row(
        modifier = Modifier.fillMaxWidth()
        .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HomeQuickActionbutton(R.drawable.outline_call_receive_crypto_24, receiveButtonText)
        HomeQuickActionbutton(R.drawable.outline_asend_crypto_24, sendButtonText)
        HomeQuickActionbutton(R.drawable.outline_swap_crypto_24, swapButtonText)
        HomeQuickActionbutton(R.drawable.outline_buy_crypto_24, buyButtonText)
    }
}


@Preview
@Composable
fun ButtonActionRowPreview() {
    ButtonActionRow()
}