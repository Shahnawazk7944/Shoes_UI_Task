package com.example.shoes_ui_task.presentation.features.products.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.spacing
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenEvents
import com.example.shoes_ui_task.presentation.features.products.viewmodels.ProductsScreenStates

@Composable
fun FilterChips(
    filters: List<String>,
    state: ProductsScreenStates,
    events: (ProductsScreenEvents) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.large,
            vertical = MaterialTheme.spacing.small
        )
    ) {
        items(filters) { filter ->
            FilterChip(
                selected = state.selectedFilter == filter,
                onClick = { events(ProductsScreenEvents.ChangeFilter(filter)) },
                label = {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (state.selectedFilter == filter) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    )
                },
                modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                shape = RoundedCornerShape(16.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    selectedContainerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductCard(
    product: Product,
    isCurrentPage: Boolean,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onProductClick: (Product) -> Unit
) {
    val targetRotation = if (isCurrentPage) -20f else -70f
    val animateRotation by animateFloatAsState(
        targetValue = targetRotation,
        label = "rotation_animation"
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .clickable { onProductClick(product) },
        colors = CardDefaults.cardColors(containerColor = product.color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                with(sharedTransitionScope) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "text-${product.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                    )
                }
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f),
                )
            }

            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.BottomEnd)
                        .rotate(animateRotation)
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${product.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )
            }
        }
    }
}


@Composable
fun ProductItem(product: Product, onProductClick: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick(product) }
            .padding(vertical = 8.dp, horizontal = MaterialTheme.spacing.mediumLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = product.name,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
            )
        }
    }
}