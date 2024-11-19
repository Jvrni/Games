package com.core.domain

import com.core.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun get() : Flow<List<Game>>
}