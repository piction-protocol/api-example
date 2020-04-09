package network.piction.example.api.serivces

import network.piction.example.api.entities.Wallet
import network.piction.example.api.repositories.WalletRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WalletService(val walletRepository: WalletRepository) {

    fun findById(id: Long): Wallet? = walletRepository.findByIdOrNull(id)

    fun save(wallet: Wallet): Wallet = walletRepository.save(wallet)
}
