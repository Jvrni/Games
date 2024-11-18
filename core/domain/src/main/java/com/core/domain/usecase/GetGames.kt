package com.core.domain.usecase

import com.core.domain.GameRepository

class GetGames(private val repository: GameRepository) {

    operator fun invoke() = repository.get()
}