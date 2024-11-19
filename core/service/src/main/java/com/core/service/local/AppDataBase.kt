package com.core.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.domain.models.Game

@Database(version = 1, entities = [Game::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao
}