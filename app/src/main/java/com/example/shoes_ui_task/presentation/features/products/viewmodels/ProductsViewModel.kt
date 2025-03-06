package com.example.shoes_ui_task.presentation.features.products.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProductsScreenStates())
    val state = _state.asStateFlow()

    fun productsScreenEvents(event: ProductsScreenEvents) {
        when (event) {
            else -> {}
        }
    }


}

