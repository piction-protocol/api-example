package network.piction.example.api.jpa

import network.piction.example.api.entities.User
import network.piction.example.api.entities.Wallet
import network.piction.example.api.repositories.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class UserJpaTest() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun 유저_생성_테스트() {
        val wallet = Wallet().apply {
            this.privateKey = "privateKey"
            this.publicKey = "publicKey"
        }

        val user = User().apply {
            this.wallet = wallet
            this.username = "username"
            this.password = "password"
            this.email = "test@piction.network"
            this.loginId = "tester"

            userRepository.save(this)
        }

        assertNotNull(user.id)
        assertNotNull(user.wallet.id)
    }

    @Test
    fun 유저_전체_내역_조회_테스트() {
        User().apply {
            this.username = "username1"
            this.password = "password1"
            this.email = "test1@piction.network"
            this.loginId = "tester1"
            this.wallet = Wallet().apply {
                this.privateKey = "privateKey1"
                this.publicKey = "publicKey1"
            }

            userRepository.save(this)
        }

        User().apply {
            this.username = "username2"
            this.password = "password2"
            this.email = "test2@piction.network"
            this.loginId = "tester2"
            this.wallet = Wallet().apply {
                this.privateKey = "privateKey2"
                this.publicKey = "publicKey2"
            }

            userRepository.save(this)
        }

        assertEquals(userRepository.count(), 2)
    }

    @Test
    fun 유저_상세_정보_조회_테스트() {

        User().apply {
            this.username = "username1"
            this.password = "password1"
            this.email = "test1@piction.network"
            this.loginId = "tester1"
            this.wallet = Wallet().apply {
                this.privateKey = "privateKey1"
                this.publicKey = "publicKey1"
            }

            userRepository.save(this)
        }

        val user = userRepository.findById(1L).get()

        assertNotNull(user.id)
        assertEquals(user.wallet.id, 1L)
        assertEquals(user.loginId, "tester1")
    }
}