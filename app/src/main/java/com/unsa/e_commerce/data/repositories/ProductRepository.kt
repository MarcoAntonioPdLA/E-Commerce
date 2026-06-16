package com.unsa.e_commerce.data.repositories

import com.unsa.e_commerce.R
import com.unsa.e_commerce.data.models.Product

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            name = "Laptop",
            description = "Laptop con pantalla de 15 pulgadas.",
            image = R.drawable.laptop,
            price = 3499.90
        ),
        Product(
            id = 2,
            name = "Mouse",
            description = "Mouse gamer con RGB.",
            image = R.drawable.mouse,
            price = 99.90
        ),
        Product(
            id = 3,
            name = "Teclado",
            description = "Teclado gamer con RGB.",
            image = R.drawable.keyboard,
            price = 129.90
        ),
        Product(
            id = 4,
            name = "Monitor",
            description = "Monitor de 24 pulgadas.",
            image = R.drawable.monitor,
            price = 499.90
        ),
        Product(
            id = 5,
            name = "Audífonos",
            description = "Audífonos inalámbricos.",
            image = R.drawable.headphones,
            price = 159.90
        ),
        Product(
            id = 6,
            name = "Tablet",
            description = "Tablet para tareas generales.",
            image = R.drawable.tablet,
            price = 1999.90
        ),
        Product(
            id = 7,
            name = "Mouse-pad",
            description = "Mouse-pad bonito.",
            image = R.drawable.mouse_pad,
            price = 29.90
        )
    )

    fun getAllProducts(): List<Product> {
        return products
    }

    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }
}