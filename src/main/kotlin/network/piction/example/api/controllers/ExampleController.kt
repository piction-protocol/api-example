package network.piction.example.api.controllers

import network.piction.example.api.entities.User
import network.piction.example.api.entities.Wallet
import network.piction.example.api.requests.CreateUserDto
import network.piction.example.api.responses.UserDto
import network.piction.example.api.serivces.UserService
import network.piction.example.api.serivces.WalletService
import network.piction.example.entities.UserEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
class ExampleController(
    val userService: UserService,
    val walletService: WalletService
) {

    @GetMapping("/users")
    fun all(): List<UserDto> = userService.findAll().map { UserDto(it) }

    @GetMapping("/users/{userId}")
    fun get(@PathVariable userId: Long): UserDto? =
        userService.findById(userId)?.let {
            UserDto(it)
        }

    @PostMapping("/users")
    fun create(@RequestBody request: CreateUserDto): UserDto {
        val wallet = Wallet().apply {
            this.publicKey = "publicKey${Random.nextInt()}"
            this.privateKey = "privateKey${Random.nextInt()}"

            walletService.save(this)
        }

        val user = User().apply {
            this.wallet = wallet
            this.loginId = request.loginId
            this.email = request.email
            this.password = request.password
            this.username = request.username
            this.status = UserEntity.STATUS.ACTIVE

            userService.save(this)
        }

        return UserDto(user)
    }
}