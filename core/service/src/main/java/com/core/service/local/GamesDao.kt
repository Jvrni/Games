package com.core.service.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.core.destinations.models.Games

@Dao
interface GamesDao {

    @Query("SELECT * FROM games") fun getAllGames(): List<com.core.destinations.models.Games>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertGames(vararg games: com.core.destinations.models.Games)

    @Update
    fun updateGames(games: com.core.destinations.models.Games)

    @Delete
    fun deleteGames(games: com.core.destinations.models.Games)
}