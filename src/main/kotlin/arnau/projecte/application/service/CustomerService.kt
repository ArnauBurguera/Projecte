package arnau.projecte.application.service

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import arnau.projecte.dto.CustomerDTO
import org.springframework.stereotype.Service
import java.util.UUID

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val customerMapper: CustomerMapper,
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun getCustomerById(id: UUID): Customer? {
        val key = "customer:$id"

        val cachedCustomer = redisTemplate.opsForValue().get(key) as? Customer //safe cast to Customer
        if (cachedCustomer != null) {
            println("this is the cached customer: $cachedCustomer")
            return cachedCustomer
        }

        val customer = customerRepository.findById(id) ?: return null

        redisTemplate.opsForValue().set(key, customer, 10, TimeUnit.MINUTES)
        return customer
    }

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