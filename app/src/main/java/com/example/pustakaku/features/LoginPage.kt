package com.example.pustakaku.features

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.ui.theme.AuthViewModel


@Composable
fun LoginPage(navController: NavController, context: Context, authviewModel: AuthViewModel) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val isLoading = authviewModel.isLoading
    val errorMessage = authviewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp), // Sesuaikan padding sesuai kebutuhan
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Sesuaikan alignment agar konten di atas lebih rapat
        ) {
            // Gambar
            Image(
                painter = painterResource(id = R.drawable.login_image), // Sesuaikan dengan gambar yang kamu pakai
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp) // Sesuaikan ukuran gambar
                    .padding(top = 40.dp) // Kurangi padding atas jika terlalu besar
            )

            Spacer(modifier = Modifier.height(20.dp)) // Kurangi jarak antara gambar dan "Sign In"

            // Text "Sign In"
            Text(
                text = "Sign In",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp)) // Kurangi jarak antara teks "Sign In" dan input email

            // Input Email
            OutlinedTextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text("Email Address") },
                placeholder = { Text("Email Address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // Input Password
            OutlinedTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text("Password") },
                placeholder = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = null,
                            tint = if (passwordVisibility.value) Color.White else Color.Gray
                        )
                    }
                },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp)) // Sesuaikan jarak antara input dan tombol

            // Tombol "Sign In"
            Button(
                onClick = { authviewModel.login(navController)},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Sign In", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Sesuaikan jarak antara tombol dan teks bawah

            if (errorMessage.value.isNotEmpty()){
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Text "Create An Account"
            Text(
                text = "Create An Account",
                modifier = Modifier.clickable(onClick = {
                    navController.navigate("Register") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
            )
        }
    }
}




//@Preview
//@Composable
//private fun LoginPrev() {
//    LoginPage()
//
//}