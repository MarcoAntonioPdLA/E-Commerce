package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.unsa.e_commerce.ui.components.ProductDetailTopAppBar
import com.unsa.e_commerce.ui.view_models.CartViewModel
import com.unsa.e_commerce.ui.view_models.ProductDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController,
    cartViewModel: CartViewModel,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    val product = viewModel.product

    Scaffold(
        topBar = { 
            ProductDetailTopAppBar(
                productName = product?.title ?: "Cargando...",
                navController = navController
            ) 
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        product?.let { p ->
            Column(modifier = Modifier.padding(paddingValues = innerPadding).fillMaxWidth().padding(horizontal = 8.dp)) {
                AsyncImage(
                    model = p.image,
                    contentDescription = p.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = p.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = p.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "S/. %.2f".format(p.price), fontSize = 20.sp)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { 
                        cartViewModel.addProduct(p)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}