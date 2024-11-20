package com.features.details

import com.core.commons.base.UnidirectionalViewModel
import com.core.designsystem.components.GameCardEntity

interface DetailsContract :
    UnidirectionalViewModel<DetailsContract.State, DetailsContract.Event, DetailsContract.Effect> {

    data class State(
        val game: GameCardEntity? = null,
        val showEditBottomSheet: Boolean = false,
        val showDeleteBottomSheet: Boolean = false
    )

    sealed class Event {
        data object OnStart : Event()
        data object OnBack : Event()
        data class OnSave(val value: String) : Event()
        data object OnDelete : Event()
        data class OnShowEditBottomSheet(val value: Boolean) : Event()
        data class OnShowDeleteBottomSheet(val value: Boolean) : Event()
    }

    sealed class Effect {
        data object OnBack : Effect()
    }
}