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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
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
    val font = FontFamily(Font(R.font.nunito_extrabold))

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate("Home") {
            popUpTo("Splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp, vertical = 30.dp)
            .background(color = Color(0xfffffbee)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(178.dp)
                .height(136.dp),
            contentScale = ContentScale.FillBounds,

        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "PUSTAKAKU",
            style = TextStyle(
                fontSize = 30.sp,
//              fontWeight = FontWeight.Black,
                fontFamily = font
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


