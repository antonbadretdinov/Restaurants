@file:Suppress("WrapUnaryOperator")

package com.example.restaurants.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.restaurants.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val sfProFontFamily = FontFamily(
    androidx.compose.ui.text.font.Font(R.font.sf_pro_bold, FontWeight.Bold),
    androidx.compose.ui.text.font.Font(R.font.sf_pro_semibold, FontWeight.SemiBold),
    androidx.compose.ui.text.font.Font(R.font.sf_pro_regular, FontWeight.Normal),
    androidx.compose.ui.text.font.Font(R.font.sf_pro_medium, FontWeight.Medium)
)

val robotoFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Roboto"),
        fontProvider = provider,
    )
)

val baseline = Typography()

val AppTypography = Typography(
    titleLarge = baseline.titleLarge.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),

    titleSmall = baseline.titleSmall.copy(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = -0.41.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),

    bodyLarge = baseline.bodyLarge.copy(
        fontFamily = sfProFontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),

    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),

    bodySmall = baseline.bodySmall.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),


    labelLarge = baseline.labelLarge.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = -0.41.sp,
        textAlign = TextAlign.Center,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Default.trim
        )
    ),

    labelMedium = baseline.labelMedium.copy(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Top,
            trim = LineHeightStyle.Default.trim
        )
    ),

    labelSmall = baseline.labelSmall.copy(fontFamily = sfProFontFamily),

    )