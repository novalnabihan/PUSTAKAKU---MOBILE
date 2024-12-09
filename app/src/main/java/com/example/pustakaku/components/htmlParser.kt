package com.example.pustakaku.components

import android.graphics.Color
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

fun String.toSpannedHtml(): Spanned {
  return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
  } else {
    Html.fromHtml(this)
  }
}

@Composable
fun HtmlText(
  html: String,
  modifier: Modifier = Modifier,
  fontSize: Float = 14f, // Default font size in SP
  lineHeight: Float = 1.5f, // Default line height
//  textColor: Color = Color(0xFFFF6500), // Default text color
  textAlign: TextAlign = TextAlign.Start // Default text alignment
) {
  AndroidView(
    modifier = modifier,
    factory = { context ->
      TextView(context).apply {
        text = html.toSpannedHtml()
        setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize) // Set font size
        setLineSpacing(0f, lineHeight) // Set line height
//        setTextColor(textColor.toArgb()) // Set text color
        gravity = when (textAlign) { // Set text alignment
          TextAlign.Start -> Gravity.START
          TextAlign.Center -> Gravity.CENTER
          TextAlign.End -> Gravity.END
          else -> Gravity.START
        }
      }
    },
    update = { textView ->
      textView.text = html.toSpannedHtml()
      textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
      textView.setLineSpacing(0f, lineHeight)
//      textView.setTextColor(textColor.toArgb())
      textView.gravity = when (textAlign) {
        TextAlign.Start -> Gravity.START
        TextAlign.Center -> Gravity.CENTER
        TextAlign.End -> Gravity.END
        else -> Gravity.START
      }
    }
  )
}


@Preview
@Composable
private fun Preview() {
    HtmlText("<b>Ini adalah teks bold</b><br>Baris baru!")
}