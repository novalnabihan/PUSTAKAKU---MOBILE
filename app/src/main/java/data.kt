import com.example.pustakaku.R

data class Item(
    val image: Int, // Resource ID for the image
    val name: String
)
val recommendItems = listOf(
    Item(image = R.drawable.ic_launcher_foreground, name = "Buku Fiksi"),
    Item(image = R.drawable.ic_launcher_foreground, name = "Buku Biografi"),
    Item(image = R.drawable.ic_launcher_foreground, name = "Thriller"),
)
