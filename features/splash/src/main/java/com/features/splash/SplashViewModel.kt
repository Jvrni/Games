package com.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecase.GetGames
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(private val getGames: GetGames) : ViewModel(), SplashContract {

    private val mutableState = MutableStateFlow(SplashContract.State())
    override val state: StateFlow<SplashContract.State> = mutableState

    private val effectFlow = MutableSharedFlow<SplashContract.Effect>()
    override val effect: SharedFlow<SplashContract.Effect> = effectFlow.asSharedFlow()

    override fun event(event: SplashContract.Event) {
        when (event) {
            is SplashContract.Event.OnStart -> onStart()
            is SplashContract.Event.OnRefresh -> onRefresh()
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            mutableState.update { SplashContract.State() }
            onStart()
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            getGames()
                .catch {
                    it.message
                    mutableState.update { it.copy(error = true) }
                }
                .collect { list ->
                    effectFlow.emit(SplashContract.Effect.NavigateToHome(list))
                }
        }
    }
}