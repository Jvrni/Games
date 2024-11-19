package com.core.domain

import com.core.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun fetch() : Flow<Unit>
    fun get() : Flow<List<Game>>
}