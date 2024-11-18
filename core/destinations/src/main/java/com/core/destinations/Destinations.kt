package com.core.destinations

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.toRoute
import com.core.commons.models.Game
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf
import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {
    @Serializable
    data object Splash: Destinations()

    @Serializable
    data object Home: Destinations()

    @Serializable
    data class Details(val games: Game): Destinations() {
        companion object {
            val typeMap = mapOf(typeOf<Game>() to serializableType<Game>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<Details>(typeMap)
        }
    }
}

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}