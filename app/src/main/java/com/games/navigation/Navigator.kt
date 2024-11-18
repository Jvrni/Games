package com.games.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.core.destinations.Destinations
import com.wygo.navigation.RouterImpl

@Composable
fun Navigator(router: RouterImpl) {
    NavHost(
        router.navigationController,
        startDestination = Destinations.Splash
    ) {
    }
}
