package com.example.cryptotracker.ui.screens.home.composables


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.R
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun WalletHomeHeader() {
    Row(
        modifier = Modifier.fillMaxSize().padding(20.dp).height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Color.Cyan
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text("🎅🏿", fontSize = 25.sp)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text("big.samuel", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.outline_qr_code_2_24), contentDescription = "", modifier = Modifier.height(50.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Icon(painter = painterResource(R.drawable.outline_notifications_unread_24), contentDescription = "")
        }

    }
}

@Preview
@Composable
fun WalletHomeHeaderPreview() {
    CryptoTrackerTheme {
        Surface {
            WalletHomeHeader()
        }
    }
}