import androidx.compose.ui.graphics.Color


data class Crypto(
    val name: String,
    val symbol: String,
    val amount: Double,
    val fiatValue: String,
    val chartTrend: String,
    val imgUrl: String = "",
    val iconColor: Color
) {

    val a = 1
    val formattedPrice: String
        get() = String.format("$%.2f", fiatValue)
}