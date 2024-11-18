package com.wygo.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.core.destinations.Destinations
import com.destinations.Router

class RouterImpl(
    navController: NavHostController
) : Router<NavHostController> {

    override val navigationController = navController

    override fun navigateTo(destination: Destinations, resetStack: Boolean, popUpStartDestination: Boolean) {
        navigationController.navigate(destination) {
            if (resetStack) popUpTo(0) { saveState = true } // reset stack
            else if (popUpStartDestination) popUpTo(navigationController.graph.findStartDestination().id) { saveState = true }

            launchSingleTop = true
            restoreState = true
        }
    }

    override fun back() { navigationController.popBackStack() }

    override fun navigationUp(): Boolean { return navigationController.navigateUp() }
}