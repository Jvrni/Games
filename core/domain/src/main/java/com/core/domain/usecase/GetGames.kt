package com.core.domain.usecase

import com.core.domain.GameRepository
import javax.inject.Inject

class GetGames @Inject constructor(private val repository: GameRepository) {

    operator fun invoke() = repository.get()
}