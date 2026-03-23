import com.example.cryptotracker.data.remote.dto.CryptoDto
import retrofit2.http.GET
import retrofit2.http.Query

// Webservice
// Endpoint

interface CoinGeckoTokensService {
    @GET("coins/markets")
    suspend fun getCryptos(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order :String = "market_cap_desc",
        @Query("per_page") perPage: Int = 250,
        @Query("page") page :Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
    ): List<CryptoDto>

    // getCryptoDetail()
}