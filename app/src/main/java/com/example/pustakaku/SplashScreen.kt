import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, context: Context) {
    val font = FontFamily(Font(R.font.nunito_extrabold))

    LaunchedEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        kotlinx.coroutines.delay(2000)
        if (currentUser != null) {
            navController.navigate("Home") {
                popUpTo("Splash") { inclusive = true }
            }
        } else {
            navController.navigate("Login") {
                popUpTo("Splash") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfffffbee)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "PUSTAKAKU",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = font
            )
        )
    }
}

