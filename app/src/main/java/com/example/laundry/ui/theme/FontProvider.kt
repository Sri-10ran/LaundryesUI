package com.example.laundry.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import com.example.laundry.R


object AppFonts {
    val afacad = FontFamily(
        Font(R.font.afacadflux_variable,FontWeight.Bold) // Make sure the weight matches the actual font
    )

    val ubuntu = FontFamily(
        Font(R.font.ubuntu_medium,FontWeight.Normal)
    )

    val poppins = FontFamily(
        Font(R.font.poppins_medium,FontWeight.Normal)
    )

    val dmsans = FontFamily(
        Font(R.font.dmsans_variable,FontWeight.Bold)
    )

    // Define reusable text styles
    val contents = TextStyle(fontFamily = afacad)
    val buttoninside = TextStyle(fontFamily = ubuntu)
    val topic = TextStyle(fontFamily = poppins)
    val subtopic = TextStyle(fontFamily = dmsans)
}
