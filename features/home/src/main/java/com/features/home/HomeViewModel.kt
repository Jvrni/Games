package com.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.designsystem.components.GameCardEntity
import com.core.designsystem.components.TabsEntity
import com.core.domain.models.Game
import com.core.domain.usecase.GetGames
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
class HomeViewModel @Inject constructor(private val getGames: GetGames) : ViewModel(), HomeContract {

    private val mutableState = MutableStateFlow(HomeContract.State())
    override val state: StateFlow<HomeContract.State> = mutableState

    private val effectFlow = MutableSharedFlow<HomeContract.Effect>()
    override val effect: SharedFlow<HomeContract.Effect> = effectFlow.asSharedFlow()

    private val games = mutableListOf<Game>()
    private val list = mutableListOf<GameCardEntity>()
    private val firstCategory = "All"

    override fun event(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnStart -> onStart()
            is HomeContract.Event.OnFilter -> onFilter(event.search, event.category)
            is HomeContract.Event.OnNavigateToDetails -> onNavigate(event.id)
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            getGames().collect { result ->
                val gameCardEntityList = result.map { game ->
                    GameCardEntity(
                        id = game.id,
                        image = game.thumbnail,
                        title = game.title,
                        description = game.description,
                        creator = game.developer,
                        genre = game.genre,
                        date = game.releaseDate,
                        url = game.url
                    )
                }

                list.clear()
                list.addAll(gameCardEntityList)

                games.clear()
                games.addAll(result)

                mutableState.update {
                    it.copy(
                        list = gameCardEntityList,
                        categories = mutableListOf(TabsEntity(firstCategory)).apply {
                            addAll(result.map { game -> game.genre }.distinct()
                                .map { value -> TabsEntity(value) })
                        })
                }
            }
        }
    }

    private fun onFilter(search: String, category: String) {
        val listFiltered = when {
            category == firstCategory && search.isEmpty() -> list
            category == firstCategory -> list.filter { item -> item.title.lowercase().contains(search.lowercase()) }
            else -> list.filter { item -> item.title.lowercase().contains(search.lowercase()) }
                .filter { item -> item.genre == category }
        }

        mutableState.update {
            it.copy(list = listFiltered, isEmpty = listFiltered.isEmpty())
        }
    }

    private fun onNavigate(id: Int) {
        viewModelScope.launch {
            effectFlow.emit(HomeContract.Effect.NavigateToDetails(games.first { it.id == id }))
        }
    }
}