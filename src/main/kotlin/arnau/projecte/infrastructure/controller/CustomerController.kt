package arnau.projecte.infrastructure.controller

import arnau.projecte.application.service.CustomerService
import arnau.projecte.domain.model.Customer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault


@RestController
@RequestMapping("/users")
class CustomerController(
        private val customerService: CustomerService
) {

    @GetMapping("/public")
    fun getPublicInfo(): ResponseEntity<String> =
            ResponseEntity.ok("This is public info")

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<Customer> =
            customerService.getCustomerById(UUID.fromString(id))?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> =
            ResponseEntity.ok(customerService.createCustomer(customer))

    @GetMapping
    fun getAllUsers(@PageableDefault(size = 10) pageable: Pageable): ResponseEntity<List<Customer>> {
        val page = customerService.getAllCustomers(pageable)
        return ResponseEntity.ok(page.content)
    }
}
/*
- Intentar veure què fa internament spring security
-Afegir docker-compose-local. On nomes tingui db per tal de que un cambio al jar i una nova build no requereixi de muntar tota la db again i només corro la app en intellij.

-Un bash script(que es com una task en llenguatge maquina) per fer el build automaticament.
-I un altre bash script per insertar 1mil usuaris a la db amb un init.sql o algo així, no cal que sigui migration de moment només crearlos amb un random UUID i
   que faci cherrypicking d'una llita de 5 noms o així. La idea final es fer un que creei com 8M i aleshores provar a fer un redis per guaradr la base a la cache o així i veure la diferencia
- https://www.youtube.com/watch?v=PczgM2L3w60 and https://www.youtube.com/watch?v=KYNR5js2cXE for more tasty security
-add envars maybe to store
-Take a look at openapi schema definitions and how can you apply them to here https://openapi-generator.tech/

*/