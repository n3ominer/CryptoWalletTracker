package com.example.cryptotracker.custom_keyboard.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.custom_keyboard.KeyEvent

@Composable
fun CustomKeyBoard(
    modifier: Modifier = Modifier,
    onKeyClicked: (KeyEvent) -> Unit,
    isShifted: Boolean = false
) {
    Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // Ensemble des lignes de lettres / touches spéciales

        // Ligne 1 (azertyuiop)
        Row(modifier = modifier.fillMaxWidth(), Arrangement.spacedBy(4.dp)) {
            val row1 = listOf('a','z','e','r','t','y','u','i','o','p')
            for(c in row1) {
                KeyButton(
                    label = if(isShifted) c.uppercaseChar().toString() else c.toString(),
                    onClick = {
                        onKeyClicked(KeyEvent.Char(c = c))
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Ligne 2 (qsdfghjklm)
        Row(modifier = modifier.fillMaxWidth(), Arrangement.spacedBy(4.dp)) {
            val row2 = listOf('q', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm')
            for(c in row2) {
                KeyButton(
                    label = if(isShifted) c.uppercaseChar().toString() else c.toString(),
                    onClick = {
                        onKeyClicked(KeyEvent.Char(c = c))
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        // Ligne 3 (MAJ | wxcvbn | SUPP)
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            KeyButton(
                label = "⬆",
                onClick = { onKeyClicked(KeyEvent.Shift) },
                modifier = Modifier.weight(1f)
            )

            val row3 = listOf('w', 'x', 'c', 'v', 'b', 'n')
            for(c in row3) {
                KeyButton(
                    label = if(isShifted) c.uppercaseChar().toString() else c.toString(),
                    onClick = {
                        onKeyClicked(KeyEvent.Char(c = c))
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            KeyButton(
                label = "Supp",
                onClick = { onKeyClicked(KeyEvent.BackSpace) },
                modifier = Modifier.padding(4.dp)
            )
        }
        // Ligne 4 (123 | SPACE | ENTER)
        Row(modifier = modifier.fillMaxWidth(), Arrangement.spacedBy(8.dp)) {
            KeyButton(
                label = "123",
                onClick = { onKeyClicked(KeyEvent.Shift) },
                modifier = Modifier.weight(1f)
            )

            KeyButton(
                label = "Space",
                onClick = { onKeyClicked(KeyEvent.Space) },
                modifier = Modifier.weight(3f)
            )

            KeyButton(
                label = "Enter",
                onClick = { onKeyClicked(KeyEvent.Enter) },
                modifier = Modifier.weight(1f)
            )
        }
    }

}

@Preview
@Composable
fun CustomKeyBoardPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        CustomKeyBoard(
            onKeyClicked = {

            }
        )
    }

}