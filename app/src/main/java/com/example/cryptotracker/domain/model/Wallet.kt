

data class Wallet (
    val id: Int = 0,
    val name: String,
    val holdings: List<Holding> = emptyList()
) {
    val totalValue: Double
        get() = holdings.sumOf { it.totalValue }

    val crytpoCount: Int
        get() = holdings.size
}