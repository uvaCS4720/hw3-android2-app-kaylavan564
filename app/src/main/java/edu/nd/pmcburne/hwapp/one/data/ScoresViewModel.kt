package edu.nd.pmcburne.hwapp.one.data
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.nd.pmcburne.hwapp.one.database.AppDatabase
import java.time.LocalDate
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hwapp.one.network.RetrofitInstance
import edu.nd.pmcburne.hwapp.one.database.toGame
import edu.nd.pmcburne.hwapp.one.database.toEntity
import kotlinx.coroutines.launch

class ScoresViewModel (
    private val db: AppDatabase
) : ViewModel() {
    var selectedDate by mutableStateOf(LocalDate.now())
    var selectedGender by mutableStateOf("men")
    var games by mutableStateOf<List<Game>>(emptyList())
    var isLoading by mutableStateOf(false)
    fun loadScores(){
        viewModelScope.launch{
            isLoading = true
            try {
                val year = selectedDate.year.toString()
                val month = selectedDate.monthValue.toString().padStart(2, '0')
                val day = selectedDate.dayOfMonth.toString().padStart(2, '0')
                val response = RetrofitInstance.api.getScores(
                    gender = selectedGender,
                    year = year,
                    month = month,
                    day = day
                )
                val loadedGames = response.games?.mapNotNull { wrapper ->
                    val apiGame = wrapper.game ?: return@mapNotNull null
                    val homeTeam = apiGame.home?.names?.short
                    val awayTeam = apiGame.away?.names?.short
                    if (apiGame.gameID == null || homeTeam == null || awayTeam == null){
                        null
                    }else{
                        Game(
                            gameID= apiGame.gameID,
                            dateKey = selectedDate.toString(),
                            gender = selectedGender,
                            homeTeam = homeTeam,
                            awayTeam = awayTeam,
                            homeScore = apiGame.home.score?.toIntOrNull(),
                            awayScore = apiGame.away.score?.toIntOrNull(),
                            status = apiGame.gameState ?: "unknown",
                            startTime = apiGame.startTime ?: apiGame.startDate,
                            period = apiGame.currentPeriod,
                            clock = apiGame.contestClock,
                            winner = when {
                                apiGame.home.winner == true -> homeTeam
                                apiGame.away.winner == true -> awayTeam
                                else -> null
                            }
                        )
                    }
                } ?: emptyList()
                games =loadedGames
                db.gameDao().deleteGamesForDate(selectedDate.toString(), selectedGender)
                db.gameDao().insertGames(loadedGames.map{it.toEntity()})
            } catch(e: Exception){
                games = db.gameDao().getGamesForDate(selectedDate.toString(), selectedGender).map{it.toGame()}
                e.printStackTrace()
            } finally{
                isLoading = false
            }
        }
    }
}