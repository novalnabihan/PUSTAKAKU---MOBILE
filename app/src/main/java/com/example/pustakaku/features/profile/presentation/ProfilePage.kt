package com.example.pustakaku.features.profile.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    val user by viewModel.userState.collectAsState()
    Log.d("ProfileScreen", "Current user: $user")

    // Pastikan `loadUser()` hanya dipanggil sekali
    LaunchedEffect(Unit) {
        Log.d("ProfileScreen", "LaunchedEffect loadUser() dipanggil")
        viewModel.loadUser()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let {
            Text(text = "Name: ${it.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Email: ${it.email}")
        } ?: run {
            Text(text = "Loading user information...")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.logout()
            navController.navigate("Login") {
                popUpTo("Splash") { inclusive = true }
            }
        }) {
            Text("Logout")
        }
    }
}


