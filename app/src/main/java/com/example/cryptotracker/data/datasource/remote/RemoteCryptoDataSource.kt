
import com.example.cryptotracker.data.local.CryptoDao
import com.example.cryptotracker.data.local.toEntity
import com.example.cryptotracker.data.remote.dto.crypto.toDomainModel
import com.example.cryptotracker.data.remote.dto.detail.toDomainModel
import com.example.cryptotracker.domain.model.CryptoDetail
import kotlinx.coroutines.delay
import retrofit2.HttpException

class RemoteCryptoDataSource(
    private val apiService: CoinGeckoTokensService,
    val cryptoDao: CryptoDao
) {

    suspend fun getCryptos(): Result<List<Crypto>> {
        val maxRetries = 3
        var currentRetry = 0
        var lastException: Exception? = null
        var delayMs = 1000L
        while (currentRetry < maxRetries) {
            try {
                val dtos = this.apiService.getCryptos()
                val crypto = dtos.map { it.toDomainModel() }
                // Save data in local database
                cryptoDao.insertAll(crypto.map { it.toEntity() })

                return Result.success(crypto)
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    lastException = Exception("Erreur 429: trop de requêtes. Nouvelle tentative dans ${delayMs / 1000}s.")
                    delay(delayMs)
                    delayMs *= 2 // Exponential backoff
                    currentRetry++
                } else {
                    return Result.failure(Exception("HTTP Error: ${e.message}"))
                }
            } catch (e: Exception) {
                return Result.failure(Exception("Erreur réseau: ${e.message}"))
            }
        }
        return Result.failure(lastException ?: Exception("Erreur inconnue lors de la récupération des cryptos."))
    }

    suspend fun getCryptoDetail(cryptoId: String): Result<CryptoDetail> {
        val maxRetries = 3
        var currentRetry = 0
        var lastException: Exception? = null
        var delayMs = 1000L
        while (currentRetry < maxRetries) {
            try {
                val dto = this.apiService.getCryptoDetail(cryptoId)
                val crypto = dto.toDomainModel()
                return Result.success(crypto)
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    lastException = Exception("Erreur 429: trop de requêtes. Nouvelle tentative dans ${delayMs / 1000}s.")
                    delay(delayMs)
                    delayMs *= 2 // Exponential backoff
                    currentRetry++
                } else {
                    return Result.failure(Exception("HTTP Error: ${e.message}"))
                }
            } catch (e: Exception) {
                return Result.failure(Exception("Erreur réseau: ${e.message}"))
            }
        }
        return Result.failure(lastException ?: Exception("Erreur inconnue lors de la récupération des cryptos."))
    }
}