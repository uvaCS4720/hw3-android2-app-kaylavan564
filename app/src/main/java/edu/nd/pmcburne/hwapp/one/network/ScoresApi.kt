package edu.nd.pmcburne.hwapp.one.network
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoresApi {
    @GET("scoreboard/basketball-{gender}/d1/{year}/{month}/{day}")
    suspend fun getScores(
        @Path("gender") gender:String,
        @Path("year") year:String,
        @Path("month") month:String,
        @Path("day") day:String,
    ): ScoreboardResponse
}