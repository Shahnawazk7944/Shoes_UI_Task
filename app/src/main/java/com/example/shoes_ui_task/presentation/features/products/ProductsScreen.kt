package com.example.shoes_ui_task.presentation.features.products


import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.components.CustomConfirmationDialog
import com.example.designsystem.components.CustomTopBar
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenEvents
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenStates
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenViewModel


@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel = hiltViewModel(),
    onProductClick: () -> Unit
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
        onProductClick = { onProductClick.invoke() },
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsScreenContent(
    state: ProductsScreenStates,
    events: (ProductsScreenEvents) -> Unit,
    onBackClick: () -> Unit,
    onProductClick: (Product) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.product.size })
    val coroutineScope = rememberCoroutineScope()
    val selectedFilter = remember { mutableStateOf("All") }
    val filters = listOf("All", "Air Max", "Presto", "Huarache")

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Filter Chips
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter.value == filter,
                        onClick = { selectedFilter.value = filter },
                        label = { Text(text = filter) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            // Horizontal Pager for Featured Products
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height(250.dp)
            ) { page ->
                val product = state.product[page]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onProductClick(product) },
                    colors = CardDefaults.cardColors(containerColor = product.color)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text = product.price,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = product.image),
                            contentDescription = product.name,
                            modifier = Modifier.size(150.dp)
                        )
                    }
                }
            }

            // List of All Products
            Text(text = "243 OPTIONS", modifier = Modifier.padding(start = 16.dp, top = 16.dp))
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(state.product) { product ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductClick(product) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = product.image),
                            contentDescription = product.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = product.price,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
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
