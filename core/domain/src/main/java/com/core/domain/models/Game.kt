package com.core.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.core.commons.base.Constants.TABLE_GAMES
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = TABLE_GAMES)
data class Game(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "developer")
    val developer: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String
): Parcelable
