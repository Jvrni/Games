package com.games.di

import androidx.lifecycle.SavedStateHandle
import com.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ENGLISH
 *
 * Creating ViewModel dependency injection with the repository, which we have the dependency on in our [serviceModule].
 *
 * .
 *
 * .
 *
 * SPANISH
 *
 * Creando la inyección de dependencia de ViewModel con el repositorio, del cual tenemos la dependencia en nuestro [serviceModule].
 */

val uiModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::HomeViewModel)
}