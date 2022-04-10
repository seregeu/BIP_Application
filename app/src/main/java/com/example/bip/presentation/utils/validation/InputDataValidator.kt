package com.example.bip.presentation.utils.validation

enum class ValidatorType{
    TYPE_PASSWORD,
    TYPE_USERNAME,
    TYPE_FIRST_NAME,
    TYPE_SECOND_NAME,
    TYPE_AVATAR_URL,
    TYPE_PHONE_NUMBER,
    TYPE_MAIL
}

class InputDataValidator: (String, ValidatorType) -> (Boolean){

    private val USERNAME_PATTERN: String = "[a-zA-Z0-9]+";
    private val NAME_PATTERN: String = "[a-zA-Z]+";
    private val EMAIL_PATTERN: String = "([A-Za-z0-9]+[.-_])*[A-Za-z0-9]+@[A-Za-z0-9-]+(\\.[A-Z|a-z]{2,})+"

    fun validatePassword(input: String):Boolean{
        return input.length>=5
    }

    private fun validateUserName(input: String):Boolean{
        val passwordMatcher = Regex(USERNAME_PATTERN)
        return ((passwordMatcher.matches(input)) and (input.length in (2..30)))
    }

    private fun validateName(input: String):Boolean{
        val passwordMatcher = Regex(NAME_PATTERN)
        return ((passwordMatcher.matches(input)) and (input.length in (1..15)))
    }

    private fun validateAvatarUrl(input: String):Boolean{
        return android.util.Patterns.WEB_URL.matcher(input).matches();
    }

    private fun validatePhoneNumber(input: String):Boolean{
        return ((android.util.Patterns.PHONE.matcher(input).matches())
                and (input.length<=15));
    }

    private fun validateEmail(input: String):Boolean{
        val passwordMatcher = Regex(EMAIL_PATTERN)
        return (passwordMatcher.matches(input))
        //return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }

    override fun invoke(input: String, type: ValidatorType): Boolean {
        return when(type){
            ValidatorType.TYPE_PASSWORD ->validatePassword(input)
            ValidatorType.TYPE_USERNAME ->validateUserName(input)
            ValidatorType.TYPE_FIRST_NAME ->validateName(input)
            ValidatorType.TYPE_SECOND_NAME ->validateName(input)
            ValidatorType.TYPE_AVATAR_URL ->validateAvatarUrl(input)
            ValidatorType.TYPE_PHONE_NUMBER ->validatePhoneNumber(input)
            ValidatorType.TYPE_MAIL ->validateEmail(input)
        }
    }

}
