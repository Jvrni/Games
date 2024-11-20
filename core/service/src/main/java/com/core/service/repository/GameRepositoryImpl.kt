package com.core.service.repository

import com.core.domain.GameRepository
import com.core.domain.models.Game
import com.core.service.local.GamesDao
import com.core.service.remote.GamesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * ENGLISH
 *
 * The Repository Pattern in Android is a design approach that provides a structured and organized way to manage data access and manipulation.
 * It serves as an intermediary between different data sources (like databases, network services) and the rest of the application.
 * The primary goal is to abstract the data access details, promoting clean architecture, code reusability, and maintainability.
 *
 * In this case, we have our repository implemented with coroutines flow to intermediate with our api ([GamesApi])
 * .
 *
 * .
 *
 * SPANISH
 *
 * El patrón de repositorio en Android es un enfoque de diseño que proporciona una forma estructurada y organizada de gestionar el acceso y la manipulación de datos.
 * Sirve como intermediario entre diferentes fuentes de datos (como bases de datos, servicios de red) y el resto de la aplicación.
 * El objetivo principal es abstraer los detalles de acceso a los datos, promoviendo una arquitectura limpia, la reutilización y el mantenimiento del código.
 *
 * En este caso, tenemos nuestro repositorio implementado con flujo de corrutinas para intermediar con nuestra api ([GamesApi])
 */

class GameRepositoryImpl @Inject constructor(
    private val service: GamesApi,
    private val dao: GamesDao
) : GameRepository {

    override fun fetch(): Flow<Unit> = flow {
        val list = dao.getAllGames()

        if (list.isNotEmpty()) {
            emit(Unit)
            return@flow
        }

        dao.insertGames(service.get().map { it.toGame() })
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override fun get() = flow { emit(dao.getAllGames()) }.flowOn(Dispatchers.IO)

    override fun update(game: Game) = flow { emit(dao.updateGames(game)) }.flowOn(Dispatchers.IO)

    override fun delete(game: Game) = flow { emit(dao.deleteGames(game)) }.flowOn(Dispatchers.IO)
}