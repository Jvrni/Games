package com.core.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.core.commons.base.Constants.TABLE_GAMES
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = TABLE_GAMES)
data class Game(
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
): Parcelable
