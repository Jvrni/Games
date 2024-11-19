package com.features.splash

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.commons.extensions.CollectInLaunchedEffect
import com.core.commons.extensions.use
import com.core.destinations.Destinations
import com.destinations.Router
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.splashGraph(router: Router<*>) {
    composable<Destinations.Splash> {
        val viewModel: SplashViewModel = koinViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(SplashContract.Event.OnStart)
        }

        effect.CollectInLaunchedEffect { dispatch ->
            when(dispatch) {
                is SplashContract.Effect.NavigateToHome -> {
//                    router.navigateTo(Destinations.Home(dispatch.games), resetStack = true)
                }
            }
        }

        SplashScreen(state, event)
    }
}