package com.example.bip.presentation.utils

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import com.example.bip.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterTextWatcher(field: TextInputEditText,
                          validatorType: RegisterDataValidator.ValidatorType,
                            btnCreate: MaterialButton): TextWatcher {
    val field = field
    val validatorType = validatorType
    val btnCreate = btnCreate

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        if (!RegisterDataValidator.validateData(p0.toString(), validatorType)){
            field.setTextColor(Color.RED)
            btnCreate.isClickable = false
        }else{
            field.setTextColor(Color.WHITE)
            btnCreate.isClickable = true
        }
    }
}