package arnau.projecte.infrastructure.web.repository

import arnau.projecte.domain.model.Customer
import java.util.*

interface CustomerRepository {
    fun findById(id: UUID): Customer?
    fun save(customer: Customer): Customer
}