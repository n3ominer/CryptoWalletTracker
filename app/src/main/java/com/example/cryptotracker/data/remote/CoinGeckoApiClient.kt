import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Configuration Retrofit

object CoinGeckoApiClient {

    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private val TIMEOUT = 30L

    val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val coinGeckoCryptoService = retrofit.create(CoinGeckoTokensService::class.java)
}