package edu.nd.pmcburne.hwapp.one.data

data class Game (
    val gameID: String,
    val dateKey: String,
    val gender: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val status: String,
    val startTime: String?,
    val period: String?,
    val clock: String?,
    val winner: String?
)