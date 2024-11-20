package com.core.domain.usecase

import com.core.domain.GameRepository
import com.core.domain.models.Game
import javax.inject.Inject

class UpdateTitle @Inject constructor(private val repository: GameRepository) {

    operator fun invoke(game: Game) = repository.update(game)
}