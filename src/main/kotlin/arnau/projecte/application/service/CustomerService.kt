package arnau.projecte.application.service

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.UUID

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class CustomerService (
        private val customerRepository: CustomerRepository
) {
    fun getCustomerById(id: UUID): Customer? = customerRepository.findById(id)
    fun createCustomer(customer: Customer): Customer = customerRepository.save(customer)

    fun getAllCustomers(pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(pageable)
    }
}