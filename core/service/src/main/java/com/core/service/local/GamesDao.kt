package com.core.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.core.domain.models.Game

@Dao
interface GamesDao {

    @Query("SELECT * FROM games") fun getAllGames(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertGames(games: List<Game>)

    @Update
    fun updateGames(games: Game)

    @Delete
    fun deleteGames(games: Game)
}