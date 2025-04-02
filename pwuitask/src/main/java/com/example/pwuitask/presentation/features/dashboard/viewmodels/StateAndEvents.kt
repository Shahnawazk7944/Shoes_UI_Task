package com.example.pwuitask.presentation.features.dashboard.viewmodels

data class DashboardScreenStates(
    val loading: Boolean = false,
    val topBarSelectedTab : String = "Dashboard"
)

sealed class DashboardScreenEvents {
    data class ChangeTopBarNavigationTab(val newTab: String) : DashboardScreenEvents()
}