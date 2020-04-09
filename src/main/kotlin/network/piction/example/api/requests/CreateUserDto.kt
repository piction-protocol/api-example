package network.piction.example.api.requests

data class CreateUserDto(
    val loginId: String,
    val email: String,
    val username: String,
    val password: String
)