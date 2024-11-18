package com.core.domain.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.core.commons.Constants.TABLE_GAMES

@Entity(tableName = TABLE_GAMES)
data class Games(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String
)
