package com.example.bip.presentation.ui.mainscreen.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfigUI(val mainButton: String, val qrCodeAction: String, val anotherButton: String, val money: String) :
    Parcelable
