package com.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.commons.extensions.decode
import com.core.designsystem.components.GameCardEntity
import com.core.destinations.Destinations
import com.core.domain.models.Game
import com.core.domain.usecase.DeleteGame
import com.core.domain.usecase.UpdateTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val updateTitle: UpdateTitle,
    private val deleteGame: DeleteGame
) : ViewModel(), DetailsContract {

    private val mutableState = MutableStateFlow(DetailsContract.State())
    override val state: StateFlow<DetailsContract.State> = mutableState

    private val effectFlow = MutableSharedFlow<DetailsContract.Effect>()
    override val effect: SharedFlow<DetailsContract.Effect> = effectFlow.asSharedFlow()

    override fun event(event: DetailsContract.Event) {
        when (event) {
            is DetailsContract.Event.OnStart ->
                onStart(Destinations.Details.from(savedStateHandle).game)

            is DetailsContract.Event.OnBack -> viewModelScope.launch {
                effectFlow.emit(DetailsContract.Effect.OnBack)
            }

            is DetailsContract.Event.OnSave -> viewModelScope.launch {
                val game = Destinations.Details.from(savedStateHandle).game.copy(title = event.value)

                updateTitle(game)
                    .collect {
                        onStart(game)
                        onShowEditBottomSheet(false)
                    }
            }

            is DetailsContract.Event.OnShowEditBottomSheet -> onShowEditBottomSheet(event.value)
            is DetailsContract.Event.OnShowDeleteBottomSheet -> onShowDeleteBottomSheet(event.value)
            is DetailsContract.Event.OnDelete -> viewModelScope.launch {
                deleteGame(Destinations.Details.from(savedStateHandle).game).collect {
                    effectFlow.emit(DetailsContract.Effect.OnBack)
                }
            }
        }
    }

    private fun onStart(game: Game) {
        viewModelScope.launch {
            mutableState.update {
                it.copy(
                    game = GameCardEntity(
                        id = game.id,
                        image = game.thumbnail.decode(),
                        title = game.title,
                        description = game.description,
                        creator = game.developer,
                        genre = game.genre,
                        date = game.releaseDate,
                        url = game.url.decode()
                    )
                )
            }
        }
    }

    private fun onShowEditBottomSheet(showBottomSheet: Boolean) {
        viewModelScope.launch {
            mutableState.update {
                it.copy(
                    showEditBottomSheet = showBottomSheet
                )
            }
        }
    }

    private fun onShowDeleteBottomSheet(showBottomSheet: Boolean) {
        viewModelScope.launch {
            mutableState.update {
                it.copy(showDeleteBottomSheet = showBottomSheet)
            }
        }
    }
}