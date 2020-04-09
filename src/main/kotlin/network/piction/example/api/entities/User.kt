package network.piction.example.api.entities

import network.piction.example.entities.UserEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User : UserEntity() {
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", nullable = false)
    lateinit var wallet: Wallet
}