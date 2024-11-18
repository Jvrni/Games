package com.core.domain.usecase

import com.core.domain.GamesRepository

class GetGames(private val repository: GamesRepository) {

    operator fun invoke() = repository.get()
}