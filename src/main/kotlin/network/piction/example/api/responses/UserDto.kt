package network.piction.example.api.responses

import network.piction.example.api.entities.User

data class UserDto(private val user: User) {
    val loginId = user.loginId
    val email = user.email
    val username = user.username
    val createdAt = user.createdAt
    val wallet = WalletDto(user.wallet)
}