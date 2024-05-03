package arnau.projecte.domain.repository

import arnau.projecte.domain.model.User
import java.util.*

interface UserRepository {
    fun findById(id: UUID): User?
    fun save(user: User): User
}