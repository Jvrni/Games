package com.games.di

import com.core.domain.usecase.GetGames
import org.koin.core.module.dsl.factoryOf
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

val domainModule = module {
    factoryOf(::GetGames)
}