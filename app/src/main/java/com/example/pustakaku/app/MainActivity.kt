package com.example.pustakaku.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pustakaku.core.navigation.Navigation
import com.example.pustakaku.ui.theme.PUSTAKAKUTheme
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        Firebase.initialize(this) // This line
        setContent {
            PUSTAKAKUTheme {
                Navigation()
            }
        }
    }
}

