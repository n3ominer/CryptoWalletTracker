package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptotracker.custom_keyboard.composables.CustomKeyBoard
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // remember --> Compose : "Remember the value of count"
            // mutableIntStateOf --> Compose : "This value needs to be observed"
            val count = rememberSaveable { mutableIntStateOf(0) }
            CryptoTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CustomKeyBoard(
                        onKeyClicked = { /*Mise à jour du state*/},
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    Text("Hello, World!")
}