package com.example.shoes_ui_task.presentation.features.productDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.components.CustomTopBar
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.designsystem.theme.spacing
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenEvents
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenStates
import com.example.shoes_ui_task.presentation.features.productDetails.viewmodels.ProductDetailsScreenViewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
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
                title = {
                    Text(
                        text = "Products Details",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

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
