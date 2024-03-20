package com.example.presentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.example.domain.models.ProductsDAO

@Composable
fun BaseScreen(vm: BaseViewModel, navController: NavController) {
    val products = vm.products.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        items(count = products.itemCount) { item ->
            products[item]?.let {
                ListItem(item = it, navController)
            }
        }
    }

    when (products.loadState.refresh) {
        is LoadState.Loading -> Loading()
        is LoadState.Error -> Error(vm)
        is LoadState.NotLoading -> {}
    }
}

@Composable
fun ListItem(item: ProductsDAO, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(route = "DetailScreen/${item.id}")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                SubcomposeAsyncImage(
                    model = item.thumbnailUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = item.title)
                Text(
                    text = item.description,
                    minLines = 3,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
fun Error(vm: BaseViewModel) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { vm.loadProducts() }) {
                Text(text = "Try Again")
            }
        },
        title = { Text(text = "No internet connection") }
    )
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
        )
    }
}