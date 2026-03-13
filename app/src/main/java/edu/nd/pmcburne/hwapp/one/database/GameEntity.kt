package edu.nd.pmcburne.hwapp.one.database
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nd.pmcburne.hwapp.one.data.Game

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
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

fun GameEntity.toGame(): Game{
    return Game(
        gameID = gameID,
        dateKey = dateKey,
        gender = gender,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeScore = homeScore,
        awayScore = awayScore,
        status = status,
        startTime = startTime,
        period = period,
        clock = clock,
        winner = winner
    )
}

fun Game.toEntity(): GameEntity{
    return GameEntity(
        gameID = gameID,
        dateKey = dateKey,
        gender = gender,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeScore = homeScore,
        awayScore = awayScore,
        status = status,
        startTime = startTime,
        period = period,
        clock = clock,
        winner = winner
    )
}