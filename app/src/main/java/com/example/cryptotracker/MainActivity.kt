package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.custom_keyboard.KeyEvent
import com.example.cryptotracker.custom_keyboard.composables.CustomKeyBoard
import com.example.cryptotracker.ui.theme.CryptoTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // remember --> Compose : "Remember the value of count"
            // mutableIntStateOf --> Compose : "This value needs to be observed"
            val text = rememberSaveable { mutableStateOf("") }
            val isShifted = rememberSaveable { mutableStateOf(false) }

            CryptoTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween ){
                        OutlinedTextField(
                            value = text.value,
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth().height(100.dp),
                            label = { Text("Entrez du text") },
                            readOnly = true
                        )

                        CustomKeyBoard(
                            isShifted = isShifted.value,
                            onKeyClicked = { key ->
                                when(key) {
                                    KeyEvent.BackSpace -> { if (!text.value.isEmpty()) text.value = text.value.dropLast(1) }
                                    KeyEvent.Enter -> text.value += "\n"
                                    KeyEvent.Shift -> isShifted.value = !isShifted.value
                                    KeyEvent.Space -> text.value += " "
                                    is KeyEvent.Char -> {
                                        val charToInsert = if(isShifted.value) key.c.uppercaseChar() else key.c
                                        text.value += charToInsert
                                        isShifted.value = false
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    CryptoTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween ){
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    label = { Text("Entrez du text") }
                )


                CustomKeyBoard(
                    onKeyClicked = { /*Mise à jour du state*/ },
                )
            }
        }
    }
}