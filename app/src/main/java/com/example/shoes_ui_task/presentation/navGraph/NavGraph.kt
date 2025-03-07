package com.example.shoes_ui_task.presentation.navGraph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoes_ui_task.presentation.features.productDetails.ProductDetailsScreen
import com.example.shoes_ui_task.presentation.features.products.ProductsScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Shoes_UI_Task_NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    SharedTransitionLayout {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Routes.ProductsScreen
        ) {
            composable<Routes.ProductsScreen> {
                ProductsScreen(
                    onProductClick = {
                        navController.navigate(Routes.ProductDetailsScreen(id = it))
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable<Routes.ProductDetailsScreen> { navBackStackEntry ->
                val product: Routes.ProductDetailsScreen = navBackStackEntry.toRoute()
                ProductDetailsScreen(
                    id = product.id,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}
