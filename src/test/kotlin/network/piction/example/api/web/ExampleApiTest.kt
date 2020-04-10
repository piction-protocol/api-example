package network.piction.example.api.web

import network.piction.example.api.controllers.ExampleController
import network.piction.example.api.entities.User
import network.piction.example.api.entities.Wallet
import network.piction.example.api.requests.CreateUserDto
import network.piction.example.api.serivces.UserService
import network.piction.example.api.serivces.WalletService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime


@RunWith(SpringRunner::class)
@WebMvcTest(ExampleController::class)
class ExampleApiTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var userService: UserService

    @MockBean
    lateinit var walletService: WalletService

    @Test
    fun 유저_조회_테스트() {
        val wallet = Wallet().apply {
            this.id = 1L
            this.privateKey = "privateKey"
            this.publicKey = "publicKey"
            this.createdAt = LocalDateTime.now()
        }

        val user = User().apply {
            this.id = 1L
            this.wallet = wallet
            this.username = "username"
            this.password = "password"
            this.email = "test@piction.network"
            this.loginId = "tester"
            this.createdAt = LocalDateTime.now()
        }



        given(userService.findById(1L)).willReturn(user)

        val result = mvc.perform(get("/users/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())

        result.andExpect(status().isOk)
            .andExpect(jsonPath("username").value("username"))
            .andExpect(jsonPath("wallet.publicKey").value("publicKey"))
    }
}