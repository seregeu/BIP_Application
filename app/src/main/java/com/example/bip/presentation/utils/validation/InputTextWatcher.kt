package com.example.bip.presentation.utils.validation

import android.text.Editable
import android.text.TextWatcher

class InputTextWatcher(val validatorType: ValidatorType,
                       val action: (Boolean)->(Unit)): TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        val validator = InputDataValidator()
        action(!validator(p0.toString(), validatorType))
    }

}
