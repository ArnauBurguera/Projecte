package arnau.projecte.application.service

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.model.CustomerRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerService (
        private val customerRepository: CustomerRepository
) {
    fun getCustomerById(id: UUID): Customer? = customerRepository.findById(id)
    fun createCustomer(customer: Customer): Customer = customerRepository.save(customer)
}