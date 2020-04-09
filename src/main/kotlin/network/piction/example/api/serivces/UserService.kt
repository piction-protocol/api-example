package network.piction.example.api.serivces

import network.piction.example.api.entities.User
import network.piction.example.api.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findByIdOrNull(id)

    fun save(user: User): User = userRepository.save(user)

}