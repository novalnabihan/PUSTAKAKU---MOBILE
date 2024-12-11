package com.example.pustakaku.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pustakaku.BottomMenu
import com.example.pustakaku.BottomMenuContent
import com.example.pustakaku.features.homepage.HomeScreen
import com.example.pustakaku.SplashScreen
import com.example.pustakaku.features.auth.data.repository.AuthRepository
import com.example.pustakaku.features.auth.domain.usecase.SignInUseCase
import com.example.pustakaku.features.auth.domain.usecase.SignUpUseCase
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthViewModel
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.LoginPage
import com.example.pustakaku.features.auth.presentation.ui.viewmodel.RegisterPage
import com.example.pustakaku.features.chapter_content.ReadingBookScreen
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.LoginPage
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.RegisterPage
//import com.example.pustakaku.features.auth.presentation.ui.LoginPage
//import com.example.pustakaku.features.auth.presentation.ui.viewmodel.AuthView
import com.example.pustakaku.features.detail_book.BookDetailScreen
import com.example.pustakaku.features.history.HistoryPage
import com.example.pustakaku.features.profile.data.repository.ProfileRepository
import com.example.pustakaku.features.profile.domain.usecase.GetUserUseCase
import com.example.pustakaku.features.profile.domain.usecase.LogoutUseCase
import com.example.pustakaku.features.profile.presentation.ProfileScreen
import com.example.pustakaku.features.profile.presentation.ProfileViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val currentRoute =
        navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route

    // Daftar menu untuk BottomMenu
    val bottomMenuItems = listOf(
        BottomMenuContent("Home", Icons.Default.Home),
        BottomMenuContent("History", Icons.Default.List),
        BottomMenuContent("Profile", Icons.Default.Person)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                if (currentRoute in listOf("Home", "History", "Profile"))
                    BottomMenu(
                        items = bottomMenuItems,
                        modifier = Modifier,
                        initialSelectedItemIndex = 0,
                        onItemClick = { index ->
                            val route = when (index) {
                                0 -> "Home"
                                1 -> "History"
                                2 -> "Profile"
                                else -> "Home"
                            }
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "Splash",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("Splash") {
                    SplashScreen(navController = navController, context = context)
                }
                composable("Login") {
                    LoginPage(
                        navController = navController,
                        context = context,
                        authviewModel = AuthViewModel(
                            SignInUseCase(AuthRepository()),
                            SignUpUseCase(AuthRepository())
                        )
                    )
                }
                composable("Register") {
                    RegisterPage(
                        navController = navController,
                        context = context,
                        authViewModel = AuthViewModel(
                            SignInUseCase(AuthRepository()),
                            SignUpUseCase(AuthRepository())
                        )
                    )
                }
                composable("Home") {
                    HomeScreen(navController = navController)
                }
                composable("History") {
                    HistoryPage(navController = navController)
                }
                composable("Profile") {
                    ProfileScreen(
                        navController = navController)
                }
                composable(
                    route = "Detail/{bookId}",
                    arguments = listOf(navArgument("bookId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                    BookDetailScreen(navController = navController, bookId = bookId)
                }
                composable(
                    route = "Detail/{bookId}/read",
                    arguments = listOf(navArgument("bookId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                    ReadingBookScreen(navController = navController, bookId = bookId)
                }
            }
        }
    }
}


