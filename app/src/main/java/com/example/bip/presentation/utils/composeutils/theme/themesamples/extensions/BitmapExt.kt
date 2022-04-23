package com.example.bip.presentation.utils.composeutils.theme.themesamples.extensions

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

fun Bitmap.generateDominantColorState(): Palette.Swatch = Palette.Builder(this)
    .resizeBitmapArea(0)
    .maximumColorCount(16)
    .generate()
    .swatches
    .maxByOrNull { swatch -> swatch.population }!!
