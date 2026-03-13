package edu.nd.pmcburne.hwapp.one.network

data class ScoreboardResponse(
    val games: List<ApiGameWrapper>
)

data class ApiGameWrapper(
    val game: ApiGame?
)
data class ApiGame(
    val gameID: String?,
    val gameState: String?,
    val startDate: String?,
    val startTime: String?,
    val currentPeriod: String?,
    val contestClock: String?,
    val home: ApiTeam?,
    val away: ApiTeam?
)

data class ApiTeam(
    val names: ApiTeamNames?,
    val score: String?,
    val winner: Boolean?
)

data class ApiTeamNames(
    val short: String?
)
