package com.example.bip.domain.entity

class UserData(
    val id: Int = -1,
    val firstName: String,
    val secondName: String,
    val isPhotographer: Boolean,
    val avatarUrl: String,
    val phoneNumber: String,
    val mail: String,
    val username: String,
    val password: String,
    val jwtToken: String = ""
) {
    class Builder(
        var firstName: String = "",
        var secondName: String = "",
        var isPhotographer: Boolean = false,
        var avatarUrl: String = "",
        var phoneNumber: String = "",
        var mail: String = "",
        var username: String = "",
        var password: String = ""
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun secondName(secondName: String) = apply { this.secondName = secondName }
        fun isPhotographer(isPhotographer: Boolean) = apply { this.isPhotographer = isPhotographer }
        fun avatarUrl(avatarUrl: String) = apply { this.avatarUrl = avatarUrl }
        fun phoneNumber(phoneNumber: String) = apply { this.phoneNumber = phoneNumber }
        fun mail(mail: String) = apply { this.mail = mail }
        fun username(username: String) = apply { this.username = username }
        fun password(password: String) = apply { this.password = password }

        fun build() = UserData(
            firstName = firstName,
            secondName = secondName,
            isPhotographer = isPhotographer,
            avatarUrl = avatarUrl,
            phoneNumber = phoneNumber,
            mail = mail,
            username = username,
            password = password
        )
    }
}
