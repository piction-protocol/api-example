package network.piction.example.api.repositories

import network.piction.example.api.entities.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository: JpaRepository<Wallet, Long>