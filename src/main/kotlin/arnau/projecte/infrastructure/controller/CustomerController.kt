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

    //afegir auth jwt: https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html
}