package arnau.projecte.infrastructure.controller

import arnau.projecte.application.service.CustomerService
import arnau.projecte.domain.model.Customer
import arnau.projecte.dto.CustomerDTO
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
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping("/users")
class CustomerController(
        private val customerService: CustomerService
) {

    //GET

    @GetMapping("/public")
    fun getPublicInfo(): ResponseEntity<String> =
            ResponseEntity.ok("This is public info")

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<Customer> =
            customerService.getCustomerById(UUID.fromString(id))?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(@PageableDefault(size = 10) pageable: Pageable): ResponseEntity<List<Customer>> {
        val page = customerService.getAllCustomers(pageable)
        return ResponseEntity.ok(page.content)
    }

    //POST

    @PostMapping("/customer_object")
    fun createCustomer(@RequestBody customerDTO: CustomerDTO): ResponseEntity<Customer> =
        ResponseEntity.ok(customerService.createCustomerFromDTO(customerDTO))

    @PostMapping("/customer_params")
    fun createCustomer(
        @RequestParam name: String,
        @RequestParam bankAccount: String
    ): ResponseEntity<Customer> =
        ResponseEntity.ok(customerService.createCustomerFromParams(name, bankAccount))
}
