package network.piction.example.api.jpa

import network.piction.example.api.entities.Wallet
import network.piction.example.api.repositories.WalletRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class WalletJpaTest() {

    @Autowired
    lateinit var walletRepository: WalletRepository

    @Test
    fun 지갑_저장_테스트() {
        val wallet = Wallet().apply {
            this.publicKey = "publicKey"
            this.privateKey = "privateKey"

            walletRepository.save(this)
        }
        assertNotNull(wallet.id)
    }

    @Test
    fun 지갑_조회_테스트() {
        Wallet().apply {
            this.publicKey = "publicKey1"
            this.privateKey = "privateKey1"

            walletRepository.save(this)
        }

        Wallet().apply {
            this.publicKey = "publicKey2"
            this.privateKey = "privateKey2"

            walletRepository.save(this)
        }

        walletRepository.findById(1).get().let {
            assertEquals(it.publicKey, "publicKey1")
            assertEquals(it.privateKey, "privateKey1")
        }

        assertEquals(walletRepository.count(), 2)
    }
}