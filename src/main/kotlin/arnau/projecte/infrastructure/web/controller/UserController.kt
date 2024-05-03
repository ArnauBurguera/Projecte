package arnau.projecte.infrastructure.web.controller

import arnau.projecte.application.service.UserService
import arnau.projecte.domain.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(
        private val userService: UserService
) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User> =
            userService.getUserById(UUID.fromString(id))?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> =
            ResponseEntity.ok(userService.createUser(user))
}