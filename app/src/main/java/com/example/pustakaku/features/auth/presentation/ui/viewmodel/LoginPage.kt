package com.example.pustakaku.features.auth.presentation.ui.viewmodel

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthViewModel


@Composable
fun LoginPage(navController: NavController, context: Context, authviewModel: AuthViewModel) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val isLoading = authviewModel.isLoading
    val errorMessage = authviewModel.errorMessage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7E7CE)), // Tambahkan warna latar belakang di sini
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Sesuaikan padding sesuai kebutuhan
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Sesuaikan alignment agar konten di atas lebih rapat
        ) {
            // Gambar
            Image(
                painter = painterResource(id = R.drawable.login_image2), // Sesuaikan dengan gambar yang kamu pakai
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
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp)) // Kurangi jarak antara teks "Sign In" dan input email

            // Input Email
            OutlinedTextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text("Email Address", color = Color.Black) },
                placeholder = { Text("Email Address", color = Color.Gray) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                textStyle = TextStyle(color = Color.Black), // Ubah warna teks

            )

            // Input Password
            OutlinedTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text("Password", color = Color.Black) },
                placeholder = { Text("Password", color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                textStyle = TextStyle(color = Color.Black), // Ubah warna teks
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = null,
                            tint = if (passwordVisibility.value) Color.Gray else Color.Black // Warna ikon sesuai status
                        )
                    }
                },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp)) // Sesuaikan jarak antara input dan tombol

            // Tombol "Sign In"
            Button(
                onClick = {
                    authviewModel.login(
                        emailValue.value,
                        passwordValue.value,
                        onSuccess = { navController.navigate("Home") }
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8E2610), // Warna latar belakang tombol (contoh: biru)
                    contentColor = Color.White // Warna teks pada tombol
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Sign In", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Sesuaikan jarak antara tombol dan teks bawah

            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Text "Create An Account"
            Text(
                text = "Belum punya akun? Buat akun",
                color = Color.Black,
                modifier = Modifier
                    .clickable(onClick = {
                    navController.navigate("Register") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
            )
        }
    }
}

