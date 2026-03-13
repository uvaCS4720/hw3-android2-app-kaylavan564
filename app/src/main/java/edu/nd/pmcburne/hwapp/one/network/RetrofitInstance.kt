package edu.nd.pmcburne.hwapp.one.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://ncaa-api.henrygd.me/"
    val api: ScoresApi by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ScoresApi::class.java)
    }
}