package com.features.home

import com.core.commons.base.UnidirectionalViewModel
import com.core.designsystem.components.GameCardEntity
import com.core.designsystem.components.TabsEntity
import com.core.domain.models.Game

interface HomeContract :
    UnidirectionalViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect> {

    data class State(
        val list: List<GameCardEntity> = emptyList(),
        val categories: List<TabsEntity> = emptyList(),
        val isEmpty: Boolean = false
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnFilter(val search: String, val category: String) : Event()
    }

    sealed class Effect {
        data class NavigateToDetails(val game: Game) : Effect()
    }
}