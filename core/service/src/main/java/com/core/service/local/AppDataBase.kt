package com.core.service.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.core.domain.models.Games

@Database(version = 1, entities = [Games::class, Games::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao
}