
package com.example.pustakaku.features.auth.presentation.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RegisterPage(navController: NavController, context: Context, authViewModel: AuthViewModel) {

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    val isLoading = authViewModel.isLoading
    val errorMessage = authViewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfffffbee))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Gambar
            Image(
                painter = painterResource(id = R.drawable.register_image),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 40.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Text "Sign Up"
            Text(
                text = "Sign Up",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Input Nama
            OutlinedTextField(
                value = authViewModel.registrationName.value,
                onValueChange = { authViewModel.registrationName.value = it },
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // Input Email
            OutlinedTextField(
                value = authViewModel.registrationEmail.value,
                onValueChange = { authViewModel.registrationEmail.value = it },
                label = { Text(text = "Email Address") },
                placeholder = { Text(text = "Email Address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )


            // Input Password
            OutlinedTextField(
                value = authViewModel.registrationPassword.value,
                onValueChange = { authViewModel.registrationPassword.value = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
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

            // Input Confirm Password
            OutlinedTextField(
                value = authViewModel.registrationConfirmPassword.value,
                onValueChange = { authViewModel.registrationConfirmPassword.value = it },
                label = { Text(text = "Confirm Password") },
                placeholder = { Text(text = "Confirm Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisibility.value = !confirmPasswordVisibility.value }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = null,
                            tint = if (confirmPasswordVisibility.value) Color.White else Color.Gray
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol "Sign Up"
            Button(
                onClick = {
                    authViewModel.register(
                        authViewModel.registrationEmail.value,
                        authViewModel.registrationPassword.value,
                        authViewModel.registrationName.value,
//                        name
                        onSuccess = { navController.navigate("Login") }
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
                Text(text = "Sign Up", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Text "Login Instead"
            Text(
                text = "Sudah punya akun? Login",
                modifier = Modifier.clickable(onClick = {
                    navController.navigate("Login") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
            )
        }
    }
}
