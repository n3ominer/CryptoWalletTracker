import com.example.cryptotracker.data.remote.dto.crypto.CryptoDto
import com.example.cryptotracker.data.remote.dto.detail.CryptoDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

// Webservice
// Endpoint

interface CoinGeckoTokensService {
    @GET("coins/markets")
    suspend fun getCryptos(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order :String = "market_cap_desc",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page :Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
    ): List<CryptoDto>

    //?ids=bitcoin
    // &vs_currencies=eur
    // &include_market_cap=true
    // &include_24hr_vol=true
    // &include_24hr_change=true
    @GET("simple/price")
    suspend fun getCryptoDetail(
        @Query("ids") id: String,
        @Query("vs_currencies") vsCurrencies: String = "usd" ,
        @Query("include_market_cap") includeMarketCap: Boolean = true,
        @Query("include_24hr_vol") include24hrVol : Boolean = true,
        @Query("include_24hr_change") include24hrChange: Boolean = true ,
    ): CryptoDetailDto
}