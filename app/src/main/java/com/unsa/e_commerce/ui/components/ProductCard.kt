package com.unsa.e_commerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsa.e_commerce.data.Product

@Composable
fun ProductCard(product: Product, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                Text(text = product.name, fontSize = 20.sp)
                Text(text = "S/. %.2f".format(product.price))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilledIconButtonSmall(
                    symbol = "-",
                    onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }
                )
                Text(quantity.toString())
                FilledIconButtonSmall(
                    symbol = "+",
                    onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }
                )
            }
        }
    }
}