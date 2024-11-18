package com.core.service.remote

import com.core.service.models.GameResponse
import retrofit2.http.GET

interface GamesApi {

    @GET("games")
    suspend fun get(): List<GameResponse>
}
