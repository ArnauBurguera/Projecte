package arnau.projecte.infrastructure.web.repository

import arnau.projecte.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}