package com.core.destinations

interface Router<T> {
    val navigationController: T
    fun navigateTo(destination: Destinations, resetStack: Boolean = false, popUpStartDestination: Boolean = false)
    fun back()
    fun navigationUp(): Boolean
}