import com.example.cryptotracker.data.datasource.local.LocalDataSource
import com.example.cryptotracker.data.datasource.mocks.getCryptoList
import com.example.cryptotracker.domain.repository.WalletRepository

class WalletRepositoryImpl(
    // Remote Data source --> Notre client HTTP
    // Local Data Source --> Local Room DB
    val local: LocalDataSource = LocalDataSource()
): WalletRepository {


    override fun getWallet(): Wallet {
        // TODO: Implement data fetch from Retrofit HTTP Client
        return local.getWallet() // MOCK
    }

}