package com.example.pustakaku.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pustakaku.features.homepage.HomeScreen
import com.example.pustakaku.SplashScreen
import com.example.pustakaku.features.auth.data.repository.AuthRepository
import com.example.pustakaku.features.auth.domain.usecase.SignInUseCase
import com.example.pustakaku.features.auth.domain.usecase.SignUpUseCase
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthViewModel
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.LoginPage
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.RegisterPage
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.LoginPage
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.RegisterPage
//import com.example.pustakaku.features.auth.presentation.ui.LoginPage
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthView
import com.example.pustakaku.features.detail_book.BookDetailScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        //TODO: Change startDestination to SplashScreen
        NavHost(navController = navController, startDestination = "Splash") {
            composable("Splash") {
                SplashScreen(navController = navController, context = context)
            }
            composable("Login") {
                LoginPage(navController = navController, context = context, authviewModel = AuthViewModel(
                    SignInUseCase(AuthRepository()), SignUpUseCase(AuthRepository())
                )
                )
            }
            composable("Register") {
                RegisterPage(navController = navController, context = context, authViewModel = AuthViewModel(
                    SignInUseCase(AuthRepository()), SignUpUseCase(AuthRepository())
                )
                )
            }
            composable("Home") {
//                HomeScreen()
                //TODO: Ref navController
                HomeScreen(navController = navController)
            }
            composable(
                route = "Detail/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                BookDetailScreen(navController = navController, bookId = bookId)
            }


        }
    }
}

