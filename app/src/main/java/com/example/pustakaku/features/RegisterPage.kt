package com.example.pustakaku.features


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R



@Composable
fun RegisterPage(navController: NavController, context: Context) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp), // Atur padding untuk keseluruhan konten
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Tempatkan elemen di atas
        ) {
            // Gambar
            Image(
                painter = painterResource(id = R.drawable.login_image),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp) // Sesuaikan ukuran gambar agar lebih proporsional
                    .padding(top = 40.dp) // Kurangi padding atas
            )

            Spacer(modifier = Modifier.height(20.dp)) // Kurangi jarak antara gambar dan teks "Sign Up"

            // Text "Sign Up"
            Text(
                text = "Sign Up",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp)) // Kurangi jarak antara teks "Sign Up" dan input

            // Input Nama
            OutlinedTextField(
                value = nameValue.value,
                onValueChange = { nameValue.value = it },
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // Input Email
            OutlinedTextField(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                label = { Text(text = "Email Address") },
                placeholder = { Text(text = "Email Address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            // Input Phone
            OutlinedTextField(
                value = phoneValue.value,
                onValueChange = { phoneValue.value = it },
                label = { Text(text = "Phone Number") },
                placeholder = { Text(text = "Phone Number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            // Input Password
            OutlinedTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = null,
                            tint = if (passwordVisibility.value) Color.White else Color.Gray
                        )
                    }
                },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                else PasswordVisualTransformation()
            )

            // Input Confirm Password
            OutlinedTextField(
                value = confirmPasswordValue.value,
                onValueChange = { confirmPasswordValue.value = it },
                label = { Text(text = "Confirm Password") },
                placeholder = { Text(text = "Confirm Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = {
                    IconButton(onClick = {
                        confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = null,
                            tint = if (confirmPasswordVisibility.value) Color.White else Color.Gray
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp)) // Kurangi jarak antara input dan tombol

            // Tombol "Sign Up"
            Button(onClick = { /* Handle sign up */ },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)) {
                Text(text = "Sign Up", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Kurangi jarak antara tombol dan teks bawah

            // Text "Login Instead"
            Text(
                text = "Login Instead",
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


//@Preview
//@Composable
//private fun LoginPrev() {
//    RegisterPage()
//
//}
