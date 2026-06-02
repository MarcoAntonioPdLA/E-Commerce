package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.ui.components.ProductDetailTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(product: Product, navController: NavController) {
    Scaffold(
        topBar = { ProductDetailTopAppBar(productName = product.name, navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxWidth().padding(horizontal = 8.dp)) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = product.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "S/. %.2f".format(product.price), fontSize = 20.sp)
        }
    }
}