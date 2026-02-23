package com.example.cryptotracker.presentation.ui.navigation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.R

@Composable
fun BottomNavigationBar(
    onSummaryCLick: () -> Unit,
    onSwitchCryptoClick: () -> Unit,
    onWalletClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // 1 ButtonNavItem
        ButtonNavItem(
            icon = R.drawable.outline_home_app_logo_24,
            isActive = false,
            label = "Summary",
            onClick = onSummaryCLick
        )

        // 1 CustomButton
        Box(
            modifier = Modifier.size(65.dp)
                .offset(y = (-8).dp)
                .background(
                    Color(0xFF6200EE),
                    shape = CircleShape
                )
                .clickable { onSwitchCryptoClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_swap_crypto_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }


        // 1 ButtonNavItem
        ButtonNavItem(
            icon = R.drawable.outline_wallet_24,
            isActive = false,
            label = "Summary",
            onClick = onWalletClick
        )
    }

}

@Composable
fun ButtonNavItem(
    icon: Int,
    isActive: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            painter = painterResource(icon),
            tint = if(isActive) Color(0xFF6200EE) else Color.Gray,
            contentDescription = stringResource(R.string.summary_button_in_bottom_nav_bar),
            modifier = Modifier.size(32.dp)
        )

        Text(
            label,
            color = if(isActive) Color(0xFF6200EE) else Color.Gray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium

        )
    }
}

@Preview
@Composable
fun ButtonNavItemPreview() {
    ButtonNavItem(
       icon = R.drawable.outline_home_app_logo_24,
        isActive = true,
        label = "Summary",
        onClick = {}
    )
}


@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        onSummaryCLick = {},
        onSwitchCryptoClick = {},
        onWalletClick = {}
    )
}