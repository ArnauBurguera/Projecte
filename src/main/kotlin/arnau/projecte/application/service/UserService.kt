package arnau.projecte.application.service

import arnau.projecte.domain.model.User
import arnau.projecte.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService (
        private val userRepository: UserRepository
) {
    fun getUserById(id: UUID): User? = userRepository.findById(id)
    fun createUser(user: User): User = userRepository.save(user)
}