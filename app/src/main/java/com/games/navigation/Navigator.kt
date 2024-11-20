package com.games.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.core.destinations.Destinations
import com.features.details.detailsGraph
import com.features.home.homeGraph
import com.features.splash.splashGraph

@Composable
fun Navigator(router: RouterImpl) {
    NavHost(
        router.navigationController,
        startDestination = Destinations.Splash
    ) {
        splashGraph(router)
        homeGraph(router)
        detailsGraph(router)
    }
}
