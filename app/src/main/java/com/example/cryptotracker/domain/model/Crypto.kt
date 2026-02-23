import androidx.compose.ui.graphics.Color


data class Crypto(
    val name: String,
    val symbol: String,
    val amount: Double,
    val fiatValue: String,
    val chartTrend: String,
    val fluctuationValue: Double = 2.00,
    val imgUrl: String = "",
    val iconColor: Color
) {
    val formattedPrice: String
        get() = String.format("$%.2f", fiatValue)
}