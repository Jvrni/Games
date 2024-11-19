package com.core.domain.usecase

import com.core.domain.GameRepository

class FetchGames(private val repository: GameRepository) {

    operator fun invoke() = repository.fetch()
}