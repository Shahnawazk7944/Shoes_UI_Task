package com.example.shoes_ui_task.presentation.features.productDetails.viewmodels

import androidx.compose.ui.graphics.Color
import com.example.shoes_ui_task.R
import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.data.ProductRepository
import com.example.shoes_ui_task.presentation.features.data.ProductSizes

val tempProduct = Product(
    id = "1",
    name = "Air Presto by you",
    price = "₹12,995",
    description = "A comfortable green shoe for everyday wear. Made with breathable material and cushioned insoles, perfect for walking and casual outings.",
    image = R.drawable.green_shoe,
    color = Color(0xFF599C99),
    sizes = listOf(
        ProductSizes(true, "UK 6"),
        ProductSizes(true, "UK 7"),
        ProductSizes(true, "UK 8"),
        ProductSizes(false, "UK 9"),
        ProductSizes(true, "UK 10"),
        ProductSizes(false, "UK 11"),
        ProductSizes(true, "UK 12"),
    )
)

data class ProductDetailsScreenStates(
    val product: List<Product> = ProductRepository.products,
    val selectedProduct: Product? = tempProduct, //emptyList(), this is just for testing previews
)

sealed class ProductDetailsScreenEvents {
    data class GetProductById(val id: String) : ProductDetailsScreenEvents()
}

