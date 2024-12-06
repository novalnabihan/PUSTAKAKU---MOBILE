package com.example.pustakaku.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pustakaku.core.navigation.Navigation
import com.example.pustakaku.ui.theme.PUSTAKAKUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PUSTAKAKUTheme {
                Navigation()
            }
        }
    }
}
