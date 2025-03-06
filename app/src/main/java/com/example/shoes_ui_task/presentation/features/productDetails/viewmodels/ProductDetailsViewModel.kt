package com.example.shoes_ui_task.presentation.features.productDetails.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductDetailsScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsScreenStates())
    val state = _state.asStateFlow()

    fun productDetailsScreenEvents(event: ProductDetailsScreenEvents) {
        when (event) {
            is ProductDetailsScreenEvents.GetProductById -> {
                _state.update { it.copy(selectedProduct = it.product.find { it.id == event.id }) }
            }
        }
    }

}
