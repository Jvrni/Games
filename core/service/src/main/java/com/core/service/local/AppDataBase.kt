package com.core.service.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.core.destinations.models.Games

@Database(version = 1, entities = [com.core.destinations.models.Games::class, com.core.destinations.models.Games::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao
}