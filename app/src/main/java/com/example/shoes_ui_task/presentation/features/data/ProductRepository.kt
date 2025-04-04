package com.example.shoes_ui_task.presentation.features.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.shoes_ui_task.R

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    @DrawableRes val image: Int,
    val color: Color,
    val sizes: List<ProductSizes>
)

data class ProductSizes(
    val isAvailable: Boolean,
    val size: String
)

object ProductRepository {

    val products: List<Product> = listOf(
        Product(
            id = "1",
            name = "Air Presto by you",
            price = "₹12,995",
            description = "A comfortable green shoe for everyday wear. Made with breathable material and cushioned insoles, perfect for walking and casual outings.",
            image = R.drawable.green_shoe,
            color = Color(0xFF599C99),
            sizes = listOf(
                ProductSizes(true,"UK 6"),
                ProductSizes(true,"UK 7"),
                ProductSizes(true,"UK 8"),
                ProductSizes(false,"UK 9"),
                ProductSizes(true,"UK 10"),
                ProductSizes(false,"UK 11"),
                ProductSizes(true,"UK 12"),
            )

        ),
        Product(
            id = "2",
            name = "Alpha Savage",
            price = "₹8,895",
            description = "A stylish red shoe for special occasions. Features a sleek design with a pointed toe and a slight heel, adding a touch of elegance to your outfit.",
            image = R.drawable.red_shoe,
            color = Color(0xFFE24C4D),
            sizes = listOf(
                ProductSizes(true,"UK 6"),
                ProductSizes(true,"UK 7"),
                ProductSizes(false,"UK 8"),
                ProductSizes(true,"UK 9"),
                ProductSizes(true,"UK 10"),
                ProductSizes(false,"UK 11"),
                ProductSizes(true,"UK 12"),
            )
        ),
        Product(
            id = "3",
            name = "KD13 EP",
            price = "₹12,995",
            description = "A classic white shoe that goes with everything. Versatile and comfortable, ideal for both casual and semi-formal events.",
            image = R.drawable.white_shoe,
            color = Color(0xFF4B81F4),
            sizes = listOf(
                ProductSizes(true,"UK 6"),
                ProductSizes(false,"UK 7"),
                ProductSizes(true,"UK 8"),
                ProductSizes(true,"UK 9"),
                ProductSizes(true,"UK 10"),
                ProductSizes(false,"UK 11"),
                ProductSizes(true,"UK 12"),
            )
        ),
        Product(
            id = "4",
            name = "Air Max 97",
            price = "₹11,897",
            description = "A vibrant yellow shoe to brighten your day. Made with durable materials and a slip-resistant sole, perfect for outdoor activities and adventures.",
            image = R.drawable.yellow_shoe,
            color = Color(0xFFFDBA62),
            sizes = listOf(
                ProductSizes(false,"UK 6"),
                ProductSizes(true,"UK 7"),
                ProductSizes(true,"UK 8"),
                ProductSizes(true,"UK 9"),
                ProductSizes(false,"UK 10"),
                ProductSizes(true,"UK 11"),
                ProductSizes(true,"UK 12"),
            )
        )
    )
}