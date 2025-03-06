package com.example.shoes_ui_task.presentation.features.productDetails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.components.CustomTopBar
import com.example.designsystem.components.PrimaryButton
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.designsystem.theme.spacing
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.data.ProductSizes
import com.example.shoes_ui_task.presentation.features.productDetails.components.ProductCarousel
import com.example.shoes_ui_task.presentation.features.productDetails.components.ProductDetailsSection
import com.example.shoes_ui_task.presentation.features.productDetails.components.ProductImageCard
import com.example.shoes_ui_task.presentation.features.productDetails.components.SelectSizeSection
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenEvents
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenStates
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductDetailsScreen(
    viewModel: ProductDetailsScreenViewModel = hiltViewModel(),
    id: String,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.productDetailsScreenEvents(
            ProductDetailsScreenEvents.GetProductById(id)
        )
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductDetailsScreenContent(
        state = state,
        onBackClick = { onBackClick.invoke() },
        events = viewModel::productDetailsScreenEvents
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreenContent(
    state: ProductDetailsScreenStates,
    events: (ProductDetailsScreenEvents) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onBackClick = onBackClick,
                title = { },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.mediumLarge)
                .verticalScroll(rememberScrollState())
        ) {
            state.selectedProduct?.let { product ->
                ProductImageCard(product)
                ProductDetailsSection(product)
                ProductCarousel(
                    products = state.product,
                    selectedProduct = product,
                    onProductSelected = { }
                )
                SelectSizeSection(
                    sizes = product.sizes,
                    onSizeSelected = { }
                )

                PrimaryButton(
                    onClick = { },
                    label = "Add to Bag",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = MaterialTheme.spacing.mediumLarge),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(16.dp),
                )
            } ?: run {
                Text(
                    text = "Product not found",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ProductsScreenContentPreview() {
    Shoes_UI_TaskTheme {
        ProductDetailsScreenContent(
            state = ProductDetailsScreenStates(),
            onBackClick = {},
            events = {}
        )
    }
}
