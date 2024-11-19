package com.features.home

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.commons.extensions.CollectInLaunchedEffect
import com.core.commons.extensions.use
import com.core.destinations.Destinations
import com.destinations.Router
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(router: Router<*>) {
    composable<Destinations.Home> {
        val viewModel: HomeViewModel = koinViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(HomeContract.Event.OnStart)
        }

        effect.CollectInLaunchedEffect { dispatch ->
            when(dispatch) {
                is HomeContract.Effect.NavigateToDetails -> {
                    router.navigateTo(Destinations.Details(dispatch.game), resetStack = true)
                }
            }
        }

        HomeScreen(state, event)
    }
}