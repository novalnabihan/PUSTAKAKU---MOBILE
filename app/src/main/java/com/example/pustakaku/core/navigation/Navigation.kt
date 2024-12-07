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
import com.example.pustakaku.features.LoginPage
import com.example.pustakaku.features.RegisterPage
import com.example.pustakaku.features.detail_book.BookDetailScreen
import com.example.pustakaku.ui.theme.AuthViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        //TODO: Change startDestination to SplashScreen
        NavHost(navController = navController, startDestination = "Home") {
            composable("Splash") {
                SplashScreen(navController = navController, context = context)
            }
            composable("Login") {
                LoginPage(navController = navController, context = context, authviewModel = AuthViewModel())
            }
            composable("Register") {
                RegisterPage(navController = navController, context = context, authViewModel = AuthViewModel())
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


