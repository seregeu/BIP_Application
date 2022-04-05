package com.example.bip.presentation.utils

class RegisterDataValidator {
    enum class ValidatorType{
        TYPE_PASSWORD,
        TYPE_USERNAME,
        TYPE_FIRST_NAME,
        TYPE_SECOND_NAME,
        TYPE_AVATAR_URL,
        TYPE_PHONE_NUMBER,
        TYPE_MAIL
    }

    fun validatePassword(input: String):Boolean{
        return input.length>=5
    }

    private fun validateUserName(input: String):Boolean{
        val passwordMatcher = Regex(USERNAME_PATTERN)
        return ((passwordMatcher.matches(input)) and (input.length in (2..30)))
    }

    private fun validateName(input: String):Boolean{
        val passwordMatcher = Regex(USERNAME_PATTERN)
        return ((passwordMatcher.matches(input)) and (input.length in (1..15)))
    }

    private fun validateAvatarUrl(input: String):Boolean{
        return android.util.Patterns.WEB_URL.matcher(input).matches();
    }

    private fun validatePhoneNumber(input: String):Boolean{
        return android.util.Patterns.PHONE.matcher(input).matches();
    }

    private fun validateEmail(input: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }

    companion object{
        fun validateData(input: String, type: ValidatorType):Boolean{
            val validator = RegisterDataValidator()
            return when(type){
                ValidatorType.TYPE_PASSWORD->validator.validatePassword(input)
                ValidatorType.TYPE_USERNAME->validator.validateUserName(input)
                ValidatorType.TYPE_FIRST_NAME->validator.validateName(input)
                ValidatorType.TYPE_SECOND_NAME->validator.validateName(input)
                ValidatorType.TYPE_AVATAR_URL->validator.validateAvatarUrl(input)
                ValidatorType.TYPE_PHONE_NUMBER->validator.validatePhoneNumber(input)
                ValidatorType.TYPE_MAIL->validator.validateEmail(input)
            }
        }
        private val USERNAME_PATTERN: String = "[a-zA-Z0-9]+";
    }
}