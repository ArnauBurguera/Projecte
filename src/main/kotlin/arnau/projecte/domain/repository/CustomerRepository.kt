package arnau.projecte.domain.repository

import arnau.projecte.domain.model.Customer
import java.util.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomerRepository {
    fun findById(id: UUID): Customer?
    fun save(customer: Customer): Customer
    fun findAll(pageable: Pageable): Page<Customer>
}