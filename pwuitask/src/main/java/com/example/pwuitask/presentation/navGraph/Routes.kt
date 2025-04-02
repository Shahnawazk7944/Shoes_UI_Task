package com.example.pwuitask.presentation.navGraph

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object DashboardScreen : Routes()

    @Serializable
    data class InteractiveLearningModuleScreen(val subjectId: String) : Routes()
}