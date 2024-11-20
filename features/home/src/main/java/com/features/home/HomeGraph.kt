package com.features.home

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.commons.extensions.CollectInLaunchedEffect
import com.core.commons.extensions.encode
import com.core.commons.extensions.use
import com.core.destinations.Destinations
import com.core.destinations.Router

fun NavGraphBuilder.homeGraph(router: Router<*>) {
    composable<Destinations.Home> {
        val viewModel: HomeViewModel = hiltViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(HomeContract.Event.OnStart)
        }

        effect.CollectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is HomeContract.Effect.NavigateToDetails -> {
                    router.navigateTo(
                        Destinations.Details(dispatch.game.copy(
                            thumbnail = dispatch.game.thumbnail.encode(),
                            url = dispatch.game.url.encode()
                        )),
                        resetStack = false
                    )
                }
            }
        }

        HomeScreen(state, event)
    }
}