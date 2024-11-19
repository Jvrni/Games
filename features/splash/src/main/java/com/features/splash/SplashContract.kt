package com.features.splash

import com.core.commons.base.UnidirectionalViewModel
import com.core.domain.models.Game

interface SplashContract :
    UnidirectionalViewModel<SplashContract.State, SplashContract.Event, SplashContract.Effect> {

    data class State(
        val error: Boolean = false
    )

    sealed class Event {
        data object OnStart : Event()
        data object OnRefresh : Event()
    }

    sealed class Effect {
        data object NavigateToHome : Effect()
    }
}