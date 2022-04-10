package com.example.bip.presentation.utils

import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bip.presentation.utils.validation.InputTextWatcher
import com.example.bip.presentation.utils.validation.ValidatorType

fun Fragment.showToast(message: String?) {
    Log.e("Toast", message ?: "")
    when {
        message.isNullOrEmpty() -> {
            showToast("Error")
        }
        else -> Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

fun TextView.addValidatorTextWatchListener(type: ValidatorType, actionButton: Button){
    this.apply {
        addTextChangedListener(
            InputTextWatcher(type){ flag ->
                run {
                    this.setValidationState(flag)
                    actionButton.setValidationState(flag)
                }
            }
        )
    }
}

fun TextView.setValidationState (state: Boolean){
    if (state) {
        this.setTextColor(Color.RED)
    } else {
        this.setTextColor(Color.WHITE)
    }
}

fun Button.setValidationState (state: Boolean){
    this.isClickable = !state
}
