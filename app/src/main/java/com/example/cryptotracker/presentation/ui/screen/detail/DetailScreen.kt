package com.example.cryptotracker.presentation.ui.screen.detail

import Crypto
import WalletViewModel
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.domain.usecase.WalletStateUi
import com.example.cryptotracker.presentation.ui.screen.home.ErrorContent
import com.example.cryptotracker.presentation.ui.screen.home.LoadingContent

// presentation/ui/screens/CryptoDetailScreen.kt
@Composable
fun CryptoDetailScreen(
    cryptoId: String,
    onNavigateBack: () -> Unit,
    viewModel: WalletViewModel = WalletViewModel()
) {
    // Récupérer l'état du crypto detail
    val detailUiState by viewModel.walletUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF8E24AA),
                        Color(0xFFBA68C8)
                    )
                )
            )
    ) {
        // Header avec bouton retour
        DetailHeader(
            onNavigateBack = onNavigateBack
        )

        // Contenu selon l'état
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
        ) {
            when (detailUiState) {
                is WalletStateUi.Loading -> {
                    LoadingContent()
                }
                is WalletStateUi.Success -> {
                    val crypto =
                        (detailUiState as WalletStateUi.Success).crypto.holdings.first().crypto
                    DetailContent(
                        crypto = crypto,
                        onAddToWallet = {
                            // À implémenter : ajouter au wallet
                        }
                    )
                }
                is WalletStateUi.Error -> {
                    val message =
                        (detailUiState as WalletStateUi.Error).message
                    ErrorContent(message = message)
                }
            }
        }
    }
}

@Composable
fun DetailHeader(
    onNavigateBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .size(40.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF6B46C1),
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            "Crypto Details",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(40.dp))  // Pour centrer le titre
    }
}

@Composable
fun DetailContent(
    crypto: Crypto,
    onAddToWallet: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // Afficher le crypto
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                color = crypto.iconColor
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        crypto.symbol,
                        fontSize = 28.sp
                    )
                }
            }

            Column {
                Text(
                    crypto.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    crypto.symbol,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Infos principales
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            DetailInfoRow(
                label = "Current Price",
                value = crypto.fiatValue
            )
            Spacer(modifier = Modifier.height(12.dp))
            DetailInfoRow(
                label = "24h Change",
                value = "${crypto.fluctuationValue}%",
                isPositive = crypto.fluctuationValue >= 0
            )
            Spacer(modifier = Modifier.height(12.dp))
            DetailInfoRow(
                label = "Amount",
                value = "${crypto.amount} ${crypto.symbol}"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bouton Ajouter au wallet
        Button(
            onClick = onAddToWallet,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6B46C1)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "Add to Wallet",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailInfoRow(
    label: String,
    value: String,
    isPositive: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            value,
            color = if (isPositive) Color(0xFF4CAF50) else Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun DetailScreenComposable() {
    CryptoDetailScreen(
        "",
        {},
    )
}