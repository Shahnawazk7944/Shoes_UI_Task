package com.example.shoes_ui_task.presentation.features.products.viewmodels

import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.data.ProductRepository

data class ProductsScreenStates(
    val loading: Boolean = false,
    val product: List<Product> = ProductRepository.products
)

sealed class ProductsScreenEvents {
}