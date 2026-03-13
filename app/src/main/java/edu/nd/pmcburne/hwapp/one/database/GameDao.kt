package edu.nd.pmcburne.hwapp.one.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Query("SELECT * FROM games WHERE dateKey = :dateKey AND gender = :gender")
    suspend fun getGamesForDate(dateKey:String, gender:String): List<GameEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games:List<GameEntity>)
    @Query("DELETE FROM games WHERE dateKey = :dateKey AND gender = :gender")
    suspend fun deleteGamesForDate(dateKey:String, gender:String)
}