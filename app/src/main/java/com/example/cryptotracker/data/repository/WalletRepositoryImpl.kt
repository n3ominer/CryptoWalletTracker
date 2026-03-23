import com.example.cryptotracker.data.datasource.local.LocalDataSource
import com.example.cryptotracker.data.datasource.mocks.getCryptoList
import com.example.cryptotracker.domain.model.CryptoDetail
import com.example.cryptotracker.domain.repository.WalletRepository

class WalletRepositoryImpl(
    // Remote Data source --> Notre client HTTP
    // Local Data Source --> Local Room DB
    val local: LocalDataSource = LocalDataSource(),
    val remote: RemoteCryptoDataSource
): WalletRepository {


    override suspend fun getWallet(): Wallet {
        //return local.getWallet() // MOCK
        val result = remote.getCryptos()
        var holdings = listOf<Holding>()
        if (result.isSuccess) {
            val cryptos: List<Crypto> = result.getOrNull() ?: listOf()
            holdings = cryptos.map { crypto ->
                Holding(
                    crypto,
                    quantity = 0.0
                )
            }
        }

        return Wallet(
            id = 0,
            name = "Wallet 0",
            holdings
        )
    }

    override suspend fun getCryptoDetail(cryptoId: String): CryptoDetail? {
        val result = remote.getCryptoDetail(cryptoId)
        if(result.isSuccess) {
            return result.getOrNull()
        }

        return null
    }


}