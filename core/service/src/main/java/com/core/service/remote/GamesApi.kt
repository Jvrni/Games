package com.core.service.remote

import com.core.service.models.GamesResponse
import retrofit2.http.GET

interface GamesApi {

    @GET("games")
    suspend fun get(): List<GamesResponse>
}
