package com.core.service.models

import com.core.commons.models.Game
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("short_description") val description: String,
    @SerializedName("game_url") val url: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("developer") val developer: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("freetogame_profile_url") val profile: Int
) {
    fun toGame() = Game(
        id = id,
        title = title,
        thumbnail = thumbnail,
        description = description,
        url = url,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate
    )
}
