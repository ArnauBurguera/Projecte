package arnau.projecte.application.service

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import arnau.projecte.dto.CustomerDTO
import org.springframework.stereotype.Service
import java.util.UUID

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
class CustomerService (
        private val customerRepository: CustomerRepository,
        private val customerMapper: CustomerMapper
) {
    fun getCustomerById(id: UUID): Customer? = customerRepository.findById(id)
    fun createCustomerFromDTO(customerDTO: CustomerDTO): Customer {
        val customer = customerMapper.toCustomer(customerDTO)
        return customerRepository.save(customer)
    }

    fun createCustomerFromParams(name: String, bankAccount: String): Customer {
        val customer = customerMapper.toCustomer(CustomerDTO(name, bankAccount))
        return customerRepository.save(customer)
    }

    fun getAllCustomers(pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(pageable)
    }
}