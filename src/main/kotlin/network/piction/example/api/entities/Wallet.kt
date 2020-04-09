package network.piction.example.api.entities

import network.piction.example.entities.WalletEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "wallets")
class Wallet : WalletEntity()