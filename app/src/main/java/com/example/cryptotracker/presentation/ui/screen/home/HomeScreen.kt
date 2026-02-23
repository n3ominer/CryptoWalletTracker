package com.example.cryptotracker.presentation.ui.screen.home

import Crypto
import Wallet
import WalletViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.R
import com.example.cryptotracker.data.datasource.mocks.getCryptoList
import com.example.cryptotracker.domain.usecase.WalletStateUi
import com.example.cryptotracker.presentation.ui.screen.home.composables.ButtonActionRow
import com.example.cryptotracker.presentation.ui.screen.home.composables.WalletHomeHeader

@Composable
fun HomeScreen(
    viewModel: WalletViewModel = WalletViewModel(),
    // TP 2 --> Gestion de Navigation
    onCryptoClick: () -> Unit
) {
    val walletUiState: WalletStateUi by viewModel.walletUiState.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WalletHomeHeader()
            Text("$ 123,123", fontSize = 50.sp, color = Color.White, fontWeight = FontWeight.Bold)
            ButtonActionRow()
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
        ) {
            when(walletUiState) {
                is WalletStateUi.Success -> {
                    val wallet = ((walletUiState as WalletStateUi.Success).crypto)
                    SuccessContent(wallet)
                }
                is WalletStateUi.Error -> {
                    val error = (walletUiState as WalletStateUi.Error).message
                    ErrorContent(error)
                }
                WalletStateUi.Loading -> LoadingContent()
            }
        }
    }
}


@Composable
fun SuccessContent(wallet: Wallet) {
    LazyColumn {
        items(
            getCryptoList(),
            key = { it.symbol }
        ) { crypto ->
            CryptoItem(crypto)
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Red
        )
    }
}

@Composable
fun ErrorContent(message: String) {
    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Error",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "Error",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
fun CryptoItem(
    crypto: Crypto
) {
    // Parent le plus exterieur
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            // Icone de la crypto

            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Color.Gray
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        ""
                    )
                }
            }
            Spacer(Modifier.width(8.dp))

            // Column pour afficher Nom, Prix de marché, fluctuation
            Column {
                Text(
                    crypto.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Row {
                    Text(crypto.fiatValue)
                    Spacer(Modifier.width(8.dp))
                    Text("${crypto.fluctuationValue}%")
                }
            }
        }

        Row {
            // Priw Formatté
            // Icone "Voir plus"

            Row {
                Text(
                    crypto.fiatValue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.outline_more_vert_24),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}



@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun HomeScreenPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8E24AA), // Violet foncé
                        Color(0xFFBA68C8)  // Violet clair
                    )
                )
            ),
        color = Color.Transparent
    ) {
        HomeScreen(WalletViewModel() , {})
    }
}