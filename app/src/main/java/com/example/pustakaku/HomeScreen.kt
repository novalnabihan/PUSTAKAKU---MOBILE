package com.example.pustakaku

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pustakaku.data.Item
import com.example.pustakaku.data.recommendItems

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.dark_gray)),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Jarak antar section
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        item {
            SearchBar()
        }

        item {
            HorizontalScrollableSection(title = "Perfect For You", items = recommendItems)
        }

        item {
            Column {
                SectionList(
                    title = "Buku Popular", items = listOf(
                        "Hit 1",
                        "Hit 2",
                        "Hit 3",
                        "Hit 4",
                        "Hit 3",
                        "Hit 4",
                        "Hit 3",
                        "Hit 4",
                        "Hit 3",
                        "Hit 4",
                        "Hit 3",
                        "Hit 4",
                        "Hit 3", "Hit 4", "Hit 3", "Hit 4", "Hit 3", "Hit 4", "Hit 3", "Hit 4"
                    )
                )
            }
        }

    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                colorResource(id = R.color.medium_gray),
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.Blue
                ),
            placeholder = {
                Text(
                    "Masukkan judul",
                    color = colorResource(id = R.color.medium_gray)
                )
            },
            maxLines = 1
        )
    }
}

@Composable
fun HorizontalScrollableSection(title: String, items: List<Item>) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = colorResource(id = R.color.medium_gray)
                        )
                ) {
                    // Background Image
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )


                    // Item Name
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp) // Padding for text
                    )
                }
            }
        }
    }
}


@Composable
fun SectionList(title: String, items: List<String>) {
    Column {
        Text(
            text = "Buku Popular",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(horizontal = 16.dp)

    ) {

        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        colorResource(id = R.color.medium_gray),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = item,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
    }}
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}