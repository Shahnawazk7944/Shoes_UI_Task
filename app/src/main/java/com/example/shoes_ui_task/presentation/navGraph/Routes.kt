package com.example.shoes_ui_task.presentation.navGraph

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object ProductsScreen : Routes()
    @Serializable
    data object ProductDetailsScreen : Routes()
}