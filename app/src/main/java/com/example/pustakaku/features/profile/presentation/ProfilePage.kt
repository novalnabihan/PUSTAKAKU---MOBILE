package com.example.pustakaku.features.profile.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pustakaku.features.profile.data.repository.ProfileRepositoryImpl
import com.example.pustakaku.features.profile.domain.usecase.GetUserUseCase
import com.example.pustakaku.features.profile.domain.usecase.LogoutUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.model.content.CircleShape
import com.example.pustakaku.R
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: androidx.navigation.NavHostController
) {
    // Inisialisasi Repository dan Use Cases
    val repository = ProfileRepositoryImpl(FirebaseFirestore.getInstance(), FirebaseAuth.getInstance())
    val getUserUseCase = remember { GetUserUseCase(repository) }
    val logoutUseCase = remember { LogoutUseCase(repository) }

    // Inisialisasi ViewModel menggunakan factory
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(getUserUseCase, logoutUseCase)
    )

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProfileUiState.Success -> {
            val user = (uiState as ProfileUiState.Success).user

            // Menggunakan UI dengan desain yang sudah dibuat
            ProfileContent(
                userName = user.name,
                userEmail = user.email,
                onLogoutClick = { viewModel.logout() }
            )
        }
        is ProfileUiState.Error -> {
            val message = (uiState as ProfileUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message, color = MaterialTheme.colorScheme.error)
            }
        }
        is ProfileUiState.LoggedOut -> {
            LaunchedEffect(Unit) {
                navController.navigate("Login") {
                    popUpTo("Profile") { inclusive = true }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileContent(
    userName: String,
    userEmail: String,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFF7E0))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Header
        Text(
            text = "PROFILE",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Profile Picture
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color(0XFFEB9323), shape = RoundedCornerShape(50)), // Bentuk lingkaran
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "Profile Picture",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Name Input Field
        InputField(label = "Name", value = userName)

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input Field
        InputField(label = "Email", value = userEmail)

        Spacer(modifier = Modifier.height(24.dp))

        // Logout Button
        LogoutButton(onLogoutClick = onLogoutClick)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = value,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LogoutButton(onLogoutClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFFEB9323), shape = RoundedCornerShape(8.dp))
            .clickable { onLogoutClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.logout), // Ganti dengan ikon logout
                contentDescription = "Logout Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Log Out",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}



