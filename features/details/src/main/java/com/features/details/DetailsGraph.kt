package com.features.details

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.commons.extensions.CollectInLaunchedEffect
import com.core.commons.extensions.use
import com.core.destinations.Destinations
import com.core.destinations.Router

fun NavGraphBuilder.detailsGraph(router: Router<*>) {
    composable<Destinations.Details>(typeMap = Destinations.Details.typeMap) {

        val viewModel: DetailsViewModel = hiltViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(DetailsContract.Event.OnStart)
        }

        effect.CollectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is DetailsContract.Effect.OnBack -> router.back()
            }
        }

        DetailsScreen(state, event)
    }
}