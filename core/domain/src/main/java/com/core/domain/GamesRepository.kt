package com.core.domain

import com.core.domain.models.Games
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun get() : Flow<List<Games>>
}