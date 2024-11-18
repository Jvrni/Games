package com.core.domain

import com.core.commons.models.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun get() : Flow<List<Game>>
}