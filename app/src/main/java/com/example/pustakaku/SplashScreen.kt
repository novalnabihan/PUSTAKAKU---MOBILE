package com.example.pustakaku


import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SplashScreen(navController: NavController, context: MainActivity) {
    val Font = FontFamily(Font(R.font.nunito_extrabold))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfffffbee)),// Pastikan padding tidak berlebihan
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Atur vertical arrangement agar logo dan teks lebih rapat
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Ganti dengan ID logo
            contentDescription = "Logo",
            modifier = Modifier
                .width(178.dp)
                .height(136.dp),
            contentScale = ContentScale.FillBounds,
            // Atur ukuran logo jika perlu
        )
        Spacer(modifier = Modifier.height(20.dp)) // Jarak kecil antara logo dan teks
        Text(
            text = "PUSTAKAKU",
            style = TextStyle(
                fontSize = 30.sp,
//                fontWeight = FontWeight.Black,
                fontFamily = Font
            )
        )
    }
}

//@Preview
//@Composable
//private fun SplashPrev() {
//    SplashScreen(navController = navController, this@MainActivity)
//
//}


