package com.example.shoes_ui_task.presentation.features.products.viewmodels

import com.example.shoes_ui_task.presentation.features.data.Product
import com.example.shoes_ui_task.presentation.features.data.ProductRepository

data class ProductsScreenStates(
    val product: List<Product> = ProductRepository.products,
    val selectedFilter: String = "All",
    val filters: List<String> = listOf("All", "Air Max", "Presto", "Huarache")
)

sealed class ProductsScreenEvents {
    data class ChangeFilter(val newFilter: String) : ProductsScreenEvents()
}