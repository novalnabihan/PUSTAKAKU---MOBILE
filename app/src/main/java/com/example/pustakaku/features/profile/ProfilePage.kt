package com.example.pustakaku.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.features.homepage.DataViewModel
import com.example.pustakaku.features.homepage.components.BookCard
import com.example.pustakaku.features.homepage.components.DynamicCard
import com.example.pustakaku.features.homepage.components.GamifiedCard
import com.example.pustakaku.features.homepage.components.GreetingText
import com.example.pustakaku.features.homepage.components.SearchBar
import java.lang.reflect.Modifier

@Composable
fun ProfilePage(navController: NavController) {

    Surface(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        color = colorResource(id = R.color.wheat)
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Profile Page")
        }
    }
}
