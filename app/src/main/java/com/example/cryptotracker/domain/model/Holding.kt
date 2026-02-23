
// Définit la quantité d'une crypto 'crypto' dans mon wallet
data class Holding(
    val crypto: Crypto,
    val quantity: Double
) {
    val totalValue: Double
        get() = this.crypto.amount * this.quantity
}