package com.example.cryptotracker.custom_keyboard.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier =
            modifier
                .height(48.dp)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                label,
                fontSize = 18.sp
            )
        }
    }


}


@Preview(name = "Prev1")
@Composable
fun KeyButtonPreview() {
    KeyButton(
        label = "a",
        onClick = {}
    )
}