package com.destinations

import com.core.destinations.Destinations

interface Router<T> {
    val navigationController: T
    fun navigateTo(destination: Destinations, resetStack: Boolean = false, popUpStartDestination: Boolean = false)
    fun back()
    fun navigationUp(): Boolean
}