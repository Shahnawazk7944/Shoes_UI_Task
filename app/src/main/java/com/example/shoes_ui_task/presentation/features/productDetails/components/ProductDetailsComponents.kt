package com.example.shoes_ui_task.presentation.features.productDetails.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.spacing
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.data.ProductSizes


@Composable
fun ProductImageCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = product.color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ProductDetailsSection(product: Product) {
    Column(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.mediumLarge),
    ) {
        var expanded by remember { mutableStateOf(false) }
        val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = product.description,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            maxLines = if (expanded) Int.MAX_VALUE else 2,
            onTextLayout = { textLayoutResult.value = it }
        )
        if ((textLayoutResult.value?.lineCount ?: 0) >= 2) {
            Box(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.extraSmall - 2.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = if (expanded) "Show less" else "Show more",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { expanded = !expanded }
                        .padding(horizontal = MaterialTheme.spacing.small)
                )
            }
        }
    }
}

@Composable
fun ProductCarousel(
    products: List<Product>,
    selectedProduct: Product,
    onProductSelected: (Product) -> Unit
) {
    var selectedProductIndex by remember { mutableIntStateOf(products.indexOf(selectedProduct)) }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(items = products, key = { it.id }) { product ->
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        selectedProductIndex = products.indexOf(product)
                        onProductSelected(product)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedProductIndex == products.indexOf(product)) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surfaceVariant.copy(
                        alpha = 0.4f
                    )
                ),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if (selectedProductIndex == products.indexOf(product)) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = product.image),
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSizeSection(
    sizes: List<ProductSizes>,
    onSizeSelected: (ProductSizes) -> Unit
) {
    var selectedSizeIndex by remember { mutableIntStateOf(0) }
    Text(
        text = "Select Size",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        itemsIndexed(sizes) { index, size ->
            Box(modifier = Modifier.padding(end = MaterialTheme.spacing.small)) {
                FilterChip(
                    enabled = size.isAvailable,
                    selected = selectedSizeIndex == index,
                    onClick = {
                        selectedSizeIndex = index
                        onSizeSelected(size)
                    },
                    label = {
                        Text(
                            text = size.size,
                            style = MaterialTheme.typography.titleMedium,
                            color = if (selectedSizeIndex == index) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary.copy(
                                alpha = 0.7f
                            ),
                            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                        )
                    },
                    modifier = Modifier.height(40.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        selectedContainerColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    border = BorderStroke(
                        width = if (selectedSizeIndex == index) 2.dp else 0.dp,
                        color = if (selectedSizeIndex == index) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary.copy(
                            alpha = 0.7f
                        )
                    )
                )
            }
        }
    }
}