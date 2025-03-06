package com.example.shoes_ui_task.presentation.features.products


import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.components.CustomConfirmationDialog
import com.example.designsystem.components.CustomTopBar
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.designsystem.theme.spacing
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.products.components.FilterChips
import com.example.shoes_ui_task.presentation.features.products.components.ProductCard
import com.example.shoes_ui_task.presentation.features.products.components.ProductItem
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenEvents
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenStates
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductsScreen(
    viewModel: ProductsScreenViewModel = hiltViewModel(),
    onProductClick: (id: String) -> Unit
) {
    val activity = LocalActivity.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showLogoutDialog by remember { mutableStateOf(false) }
    BackHandler {
        showLogoutDialog = true
    }
    ProductsScreenContent(
        state = state,
        onBackClick = {
            showLogoutDialog = true
        },
        onProductClick = { onProductClick.invoke(it.id) },
        events = viewModel::productsScreenEvents
    )
    if (showLogoutDialog) {
        CustomConfirmationDialog(
            icon = Icons.AutoMirrored.Filled.Logout,
            title = "Oh no! You’re leaving...",
            message = "Are you sure? Please don't go",
            confirmButtonText = "Yes, Kick me Out",
            dismissButtonText = "Nah, Just Kidding",
            onConfirm = {
                showLogoutDialog = false
                if (activity?.isTaskRoot == true) {
                    activity.finishAndRemoveTask()
                }
            },
            onDismiss = { showLogoutDialog = false }
        )
    }
}

@Composable
fun ProductsScreenContent(
    state: ProductsScreenStates,
    events: (ProductsScreenEvents) -> Unit,
    onBackClick: () -> Unit,
    onProductClick: (Product) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.product.size })

    Scaffold(
        topBar = {
            CustomTopBar(
                onBackClick = onBackClick,
                title = { },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item(key = "header") {
                Text(
                    text = "Shoes",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.small
                    )
                )
            }
            item(key = "filters") {
                FilterChips(state.filters, state, events)
            }
            item(key = "products") {
                HorizontalPager(
                    key = { it },
                    state = pagerState,
                    pageSize = PageSize.Fixed(330.dp),
                    contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.mediumLarge),
                    pageSpacing = MaterialTheme.spacing.mediumLarge,
                    modifier = Modifier.height(400.dp)
                ) { page ->
                    ProductCard(
                        product = state.product[page],
                        isCurrentPage = page == pagerState.currentPage,
                        onProductClick = onProductClick
                    )
                }
            }
            item(key = "options") {
                Text(
                    text = "${state.product.size}  OPTIONS",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.large,
                        bottom = MaterialTheme.spacing.small
                    )
                )
            }
            items(state.product, key = { it.id }) { product ->
                ProductItem(product, onProductClick)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ProductsScreenContentPreview() {
    Shoes_UI_TaskTheme {
        ProductsScreenContent(
            state = ProductsScreenStates(),
            onBackClick = {},
            events = {},
            onProductClick = {}
        )
    }
}
