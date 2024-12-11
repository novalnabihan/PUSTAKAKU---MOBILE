package com.example.pustakaku.features.profile.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    navController: androidx.navigation.NavHostController
) {
    // Inisialisasi Repository dan Use Cases
    val repository = ProfileRepositoryImpl(FirebaseFirestore.getInstance(), FirebaseAuth.getInstance())
    val getUserUseCase = remember {GetUserUseCase(repository)}
    val logoutUseCase = remember {LogoutUseCase(repository)}

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Nama: ${user.name}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${user.email}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { viewModel.logout() }) {
                    Text("Logout")
                }
            }
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


