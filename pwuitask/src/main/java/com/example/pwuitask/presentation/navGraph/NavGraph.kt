package com.example.pwuitask.presentation.navGraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pwuitask.presentation.features.dashboard.DashboardScreen
import com.example.pwuitask.presentation.features.learningModule.LearningModuleScreenScreen


@Composable
fun PW_UI_Task_NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Routes.InteractiveLearningModuleScreen("NA")
    ) {
        composable<Routes.DashboardScreen> {
            DashboardScreen(
                onRouteChange = { id -> navController.navigate(Routes.InteractiveLearningModuleScreen(id)) }
            )
        }
        composable<Routes.InteractiveLearningModuleScreen> { navBackStackEntry ->
            val module: Routes.InteractiveLearningModuleScreen = navBackStackEntry.toRoute()
            LearningModuleScreenScreen(
                subjectId = module.subjectId,
                onBackClick = { navController.navigateUp() }
            )
        }
    }

}
