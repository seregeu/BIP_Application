package com.example.bip.presentation.utils.composeutils.theme

import com.example.bip.presentation.utils.composeutils.theme.themesamples.ColorPallet

data class AppThemeState(
    var darkTheme: Boolean = false,
    var pallet: ColorPallet = ColorPallet.GREEN
)
