package network.piction.example.api.responses

import network.piction.example.api.entities.Wallet

data class WalletDto(private val wallet: Wallet) {
    val publicKey = wallet.publicKey
    val createdAt = wallet.createdAt
}