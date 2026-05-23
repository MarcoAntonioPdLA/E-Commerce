package com.unsa.e_commerce.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unsa.e_commerce.ui.components.MyNavigationBar
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.components.PrimaryButton
import com.unsa.e_commerce.ui.components.ProductList
import com.unsa.e_commerce.ui.components.SearchBar

@Composable
fun HomeScreen() {
    var text: String by remember { mutableStateOf("") }
    val products = listOf("Laptop", "Mouse", "Teclado", "Monitor")

    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { MyNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(paddingValues = innerPadding)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    value = "Buscar productos",
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PrimaryButton(
                    onClick = {},
                    modifier = Modifier.wrapContentWidth(),
                    text = "Buscar"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProductList(products)
        }
    }
}