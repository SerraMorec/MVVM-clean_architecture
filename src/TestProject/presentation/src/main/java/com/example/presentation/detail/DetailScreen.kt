package com.example.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.domain.models.DetailProductDAO

@Composable
fun DetailScreen(vm: DetailViewModel, productID: Int) {
    vm.SetId(productID)
    val details = vm.details.collectAsState()
    when (details.value) {
        is State.Success -> Details((details.value as State.Success).data)
        is State.Error -> Error(vm, productID)
        is State.Loading -> Loading()
        else -> {}
    }
}

@Composable
fun Details(product: DetailProductDAO) {
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(4.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row {
                Thumbnail(thumb = product.thumbnailUrl)
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Title(tittle = product.title)
                    Description(desc = product.description)
                }
            }
            Row {
                OtherInformation(product = product)
            }
            Images(images = product.images)
        }
    }
}


@Composable
fun Thumbnail(thumb: String) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    )
    {
        SubcomposeAsyncImage(
            model = thumb,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}

@Composable
fun Title(tittle: String) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = tittle,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Description(desc: String) {
    Text(
        modifier = Modifier
            .padding(4.dp),
        text = desc,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Images(images: List<String?>) {
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(images.size) { item ->
            images[item]?.let {
                ImageItem(it)
            }
        }
    }
}

@Composable
fun ImageItem(url: String?) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun OtherInformation(product: DetailProductDAO) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Category:   " + product.category,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Brand:   " + product.brand,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Price:   " + product.price.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Rating:   " + product.rating.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Discount %:   " + product.discount.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Stock:   " + product.stock.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Error(vm: DetailViewModel, id: Int) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { vm.loadDetails(id) }) {
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
