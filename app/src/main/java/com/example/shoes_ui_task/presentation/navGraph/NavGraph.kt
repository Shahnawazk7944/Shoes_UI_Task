package com.example.shoes_ui_task.presentation.navGraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoes_ui_task.presentation.features.productDetails.ProductDetailsScreen
import com.example.shoes_ui_task.presentation.features.products.ProductsScreen


@Composable
fun Shoes_UI_Task_NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Routes.ProductsScreen
    ) {
        composable<Routes.ProductsScreen> {
            ProductsScreen(
                onProductClick = {
                    navController.navigate(Routes.ProductDetailsScreen)
                }
            )
        }
        composable<Routes.ProductDetailsScreen> {
            ProductDetailsScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
